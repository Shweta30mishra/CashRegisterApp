package com.example.cashregisterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryRecycleActivity extends AppCompatActivity {
    RecyclerView HistoryRecycle;
    ArrayList<History> MainHistoryList;
    TextView TextviewCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_recycle);
        HistoryRecycle = (RecyclerView) findViewById(R.id.historyRecycle);
        TextviewCheck = (TextView)findViewById(R.id.textView);

        if (!(this.getIntent().getExtras().getParcelableArrayList("MyNewHistoryList").isEmpty())) {
            MainHistoryList = this.getIntent().getExtras().getParcelableArrayList("MyNewHistoryList");
            HistoryRecycle.setLayoutManager(new LinearLayoutManager(this));
            HistoryRecycle.setAdapter(new HistoryRecycleAdapter(this, MainHistoryList, new HistoryRecycleAdapter.OnItemClickListner() {
                @Override
                public void onItemClick(History history) {
                    Intent intent = new Intent(getApplicationContext(),HistoryDetailActivity.class);
                    intent.putExtra("key",history);
                    startActivity(intent);
                }
            }));
          //  HistoryRecycleAdapter adapter = new HistoryRecycleAdapter(this, MainHistoryList);
          //  HistoryRecycle.setAdapter(adapter);

        }
        else{TextviewCheck.setVisibility(View.VISIBLE);
            TextviewCheck.setText("No History List!!!");
        }
    }
}