package com.navis.locationandmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.icu.text.UnicodeSetSpanner;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.security.Permission;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int ACCESS_FINE_LOATION_REQUEST = 1;
    FusedLocationProviderClient fusedClient = null;
    GoogleMap map = null;
    OnMapReadyCallback onMapReadyCallback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            map = googleMap;
            map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    Toast.makeText(MainActivity.this, latLng.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            map.setOnPoiClickListener(new GoogleMap.OnPoiClickListener() {
                @Override
                public void onPoiClick(PointOfInterest pointOfInterest) {
                    Toast.makeText(MainActivity.this, pointOfInterest.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    ArrayList<LatLng> listPoints = new ArrayList<>();

    LocationCallback locationCB = new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult locationResult) {
            String result = locationResult.getLastLocation().getLatitude() + ", " +
                            locationResult.getLastLocation().getLongitude();
            LatLng ll = new LatLng( locationResult.getLastLocation().getLatitude(),
                                    locationResult.getLastLocation().getLongitude());
            listPoints.add(ll);

            map.moveCamera(CameraUpdateFactory.newLatLng(ll));
            map.clear();
            PolylineOptions polylineOptions = new PolylineOptions();
            for (int i = 0;i < listPoints.size();i++)
            {
                polylineOptions.add(listPoints.get(i));
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(listPoints.get(i));
                map.addMarker(markerOptions);
            }
            polylineOptions.color(Color.RED);
            map.addPolyline(polylineOptions);
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == ACCESS_FINE_LOATION_REQUEST)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //Da co the lay vi tri
                AccessLocation();
            }
        }
    }

    void AccessLocation()
    {
        Task<Location> lastLocationTask = fusedClient.getLastLocation();
        lastLocationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                String result = location.getLatitude() +
                                ", " +
                                location.getLongitude();
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
            }
        });

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        fusedClient.requestLocationUpdates(locationRequest, locationCB, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fusedClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(this, "Not Granted", Toast.LENGTH_SHORT).show();
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_FINE_LOATION_REQUEST);
        }else {
            //Da co the lay vi ri
            Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
            AccessLocation();
        }
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(onMapReadyCallback);
    }
}
