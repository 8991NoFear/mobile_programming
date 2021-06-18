package vn.binhld.recyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.MyListItemClickListener {

    private static final int NUM_LIST_ITEMS = 100;

    private MyRecyclerViewAdapter mAdapter;
    private RecyclerView mNumbersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNumbersList = (RecyclerView) findViewById(R.id.rv_numbers);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this); // By default, if you don't specify an orientation, you get a vertical list
        //  RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        //  RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mNumbersList.setLayoutManager(layoutManager);

        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        mNumbersList.setHasFixedSize(true);

        mAdapter = new MyRecyclerViewAdapter(NUM_LIST_ITEMS, this);

        mNumbersList.setAdapter(mAdapter);

        Button btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onListItemClickListener(int clickedItemIndex) {
        Toast.makeText(this, String.valueOf(clickedItemIndex), Toast.LENGTH_SHORT).show();
    }
}
