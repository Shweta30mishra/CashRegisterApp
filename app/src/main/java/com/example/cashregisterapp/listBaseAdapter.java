package com.example.cashregisterapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class listBaseAdapter extends BaseAdapter {

    List<Product> productList;
    Context context;
    LayoutInflater inflater;
    public listBaseAdapter(List<Product> productList,Context context) {
        this.context = context;
        this.productList = productList;
        inflater = (LayoutInflater.from(context));

    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       convertView = inflater.inflate(R.layout.list_view,null);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView quantity =(TextView) convertView.findViewById(R.id.quan);
        TextView price = (TextView) convertView.findViewById(R.id.product_price);
        name.setText(productList.get(position).getName());
        quantity.setText(String.valueOf(productList.get(position).getQuantity()));
        price.setText(String.valueOf(productList.get(position).getPrice()));
        return convertView;
    }
}
