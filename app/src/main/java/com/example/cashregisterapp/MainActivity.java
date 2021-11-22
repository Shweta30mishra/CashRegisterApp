package com.example.cashregisterapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView name;
    TextView quantity;
    TextView price;
    ListView productListview;
    listBaseAdapter customAdapter;
    TextView productname;
    TextView totalBill;
    TextView productquantity;
    Button clearText;
   // static StoreManager listobj = new StoreManager();
    String calcStr = "";
    Product productselected;
    int userQuantity;
    int selectedindex;
    double total;

    AlertDialog.Builder builder;
    ArrayList<History> historylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (TextView) findViewById(R.id.name);
        quantity = (TextView) findViewById(R.id.quan); // QUANTITY SHOWING IN lIST
        price = (TextView) findViewById(R.id.product_price);
        clearText = (Button) findViewById(R.id.clear);
        productname = (TextView) findViewById(R.id.Productname);
        totalBill = (TextView) findViewById(R.id.Total);
        productquantity = (TextView) findViewById(R.id.prodQuantity);
        productListview = (ListView) findViewById(R.id.list_items);
        customAdapter = new listBaseAdapter(((MyApp) getApplication()).getmanager().listOfItems, getBaseContext());
        productListview.setAdapter(customAdapter);
        builder = new AlertDialog.Builder(this);

//((MyApp) getApplication()).getmanager()
        productListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedindex = position;
                productname.setText(((MyApp) getApplication()).getmanager().listOfItems.get(position).getName());
                productselected = ((MyApp) getApplication()).getmanager().listOfItems.get(position);
                // customAdapter.notifyDataSetChanged();

            }

        });

    }

    public void btnclicked(View v) {
        if (v == clearText) {
            calcStr = "";
            productquantity.setText("");
        } else {
            String data = ((Button) v).getText().toString();
            calcStr += data;
            productquantity.setText(calcStr);
            userQuantity = Integer.parseInt(calcStr);
            updateTotal();
        }
    }

    void updateTotal() {
        int userselectedquattity = Integer.parseInt(productquantity.getText().toString());
        double price = ((MyApp) getApplication()).getmanager().listOfItems.get(selectedindex).getPrice();
        total = price * userselectedquattity;
        totalBill.setText(String.valueOf(total));

    }

    public void buyClick(View view) {
        if (!(productname.getText().toString().isEmpty()) && !(productquantity.getText().toString().isEmpty())) {
            int userSelectedQuantity = Integer.parseInt(productquantity.getText().toString());
            if (!((MyApp) getApplication()).getmanager().checkQuantity(((MyApp) getApplication()).getmanager().listOfItems.get(selectedindex), userSelectedQuantity)) {
                Toast.makeText(this, "no enough quantity in the stock", Toast.LENGTH_SHORT).show();
            } else {
                int newQuantity = ((MyApp) getApplication()).getmanager().listOfItems.get(selectedindex).getQuantity() - userQuantity;
                ((MyApp) getApplication()).getmanager().listOfItems.get(selectedindex).setQuantity(newQuantity);
                customAdapter.notifyDataSetChanged();
                String name = ((MyApp) getApplication()).getmanager().listOfItems.get(selectedindex).getName();
                double total = Double.parseDouble(String.valueOf(totalBill.getText()));
                History hisobj = new History(name, userSelectedQuantity, total, (new Date().toString()));
                historylist.add(hisobj);

                builder.setTitle("Thank you for your purchase");
                builder.setMessage("Your Purchase is" +" " +userSelectedQuantity + " " + hisobj.getName() + "for" +" "+total);
                builder.show();
            }
        }else {
            Toast.makeText(this, "all fields are required", Toast.LENGTH_SHORT).show();
        }
    }



    public void buttonPressed (View view){
            Intent intent = new Intent(this, ManageActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("MyHistoryList", historylist);
            System.out.println("main activity" + historylist.toString());
            intent.putExtras(bundle);
            startActivity(intent);
        }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    }





