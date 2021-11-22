package com.example.cashregisterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ManageActivity extends AppCompatActivity {
    Button historyButton;
    Button Restock;
    ArrayList<History> newHistory = new ArrayList<>();
    ArrayList<History> passedHistory = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_history);
        historyButton = findViewById(R.id.btu_history);
        Restock = findViewById(R.id.btu_restock);
        // this.getIntent().getExtras().putParcelableArrayList("MyHistoryList");  //receive arraylist
     if(!(this.getIntent().getExtras().getParcelableArrayList("MyHistoryList").isEmpty())) {
         newHistory = this.getIntent().getExtras().getParcelableArrayList("MyHistoryList");
         System.out.println("my manage activity"+newHistory);
     }
     else{
         System.out.println("Wrong method");
     }

    }

    public void historyOnClick(View view) {
        passedHistory = newHistory;
    Intent newintent = new Intent(this,HistoryRecycleActivity.class);
        Bundle bundle = new Bundle();
      //  bundle.getParcelableArrayList("MyHistoryList");
        bundle.putParcelableArrayList("MyNewHistoryList",passedHistory);
        System.out.println("Historypanel"+passedHistory.toString());
        newintent.putExtras(bundle);
        startActivity(newintent);

    }
    public void restockOnClick(View view) {
        Intent intent = new Intent(this,RestockActivity.class);
        startActivity(intent);
    }
}