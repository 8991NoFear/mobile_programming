package vn.binhld.dynamiclistrecyclerview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    int number;
    List<String> data;

    RecyclerView mList;
    MyRecyclerViewAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    Button btnAdd;
    Button btnSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<>();

        mList = findViewById(R.id.rv_list);
        mAdapter = new MyRecyclerViewAdapter(data);
        mList.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        mList.setLayoutManager(mLayoutManager);

        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number++;
                data.add(0, "item #" + String.valueOf(number));
                mAdapter.notifyItemInserted(0); // data bi thay doi o dau thi phai notify o day
                mList.smoothScrollToPosition(0); // luc bi luc khong bi "Passed over target position while smooth scrolling"
//                mList.scrollToPosition(0);
                mList.stopScroll();
                Log.d(TAG, "Add: " + String.valueOf(data.size()));
            }
        });

        btnSub = findViewById(R.id.btn_sub);

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                data.remove(data.size() - 1);
//                mAdapter.notifyItemRemoved(data.size());
//                mList.smoothScrollToPosition(data.size() - 1);

                data.remove(0);
                mAdapter.notifyItemRemoved(0);
//                mList.smoothScrollToPosition(0); // LOI "Passed over target position while smooth scrolling"
                Log.d(TAG, "Sub: " + String.valueOf(data.size()));
            }
        });

    }
}