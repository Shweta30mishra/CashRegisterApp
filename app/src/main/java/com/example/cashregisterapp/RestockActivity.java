package com.example.cashregisterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RestockActivity extends AppCompatActivity {
    ArrayList<Product> Itemlist = new ArrayList<>();
    Button Save, cancel;
    ListView ItemListView;
    listBaseAdapter RestockAdapter;
    EditText reStockQuantity;
    TextView quantity;
    TextView productname;
    int index = 0, addQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);
        Save = findViewById(R.id.btu_save);
        cancel = findViewById(R.id.btu_cancel);
        reStockQuantity = findViewById(R.id.addQuanText);
        quantity = (TextView) findViewById(R.id.quan);
        productname = (TextView)findViewById(R.id.name);
        Itemlist = ((MyApp) getApplication()).getmanager().listOfItems;
        ItemListView = findViewById(R.id.restockList);
        RestockAdapter = new listBaseAdapter(Itemlist, getBaseContext());
        ItemListView.setAdapter(RestockAdapter);
        ItemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
            }

        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if ((productname.getText().toString().isEmpty()) || (reStockQuantity.getText().toString().isEmpty()))
                if ((reStockQuantity.getText().toString().isEmpty())) {
                   // Toast.makeText(this,"All fields are required",Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else {
                    int addQuantity = Integer.parseInt(reStockQuantity.getText().toString()) + Itemlist.get(index).getQuantity();
                    Itemlist.get(index).setQuantity(addQuantity);

                    RestockAdapter.notifyDataSetChanged();
                    System.out.println("restock activity" + addQuantity);
                }
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
