package com.example.cashregisterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HistoryDetailActivity extends AppCompatActivity {
TextView detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
        detail = findViewById(R.id.detailTextview);
        History newObj = getIntent().getParcelableExtra("key");
        String newstring="Product :"+ newObj.getName()+"\n"+"Price:"+newObj.getPrice()+"\n"+newObj.getDate();
       detail.setText(newstring);

    }
}