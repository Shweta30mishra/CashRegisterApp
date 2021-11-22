package com.example.cashregisterapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryRecycleAdapter extends RecyclerView.Adapter<HistoryRecycleAdapter.ViewHolder>{
    Context context;
    ArrayList<History> HistoryList1;
    public interface OnItemClickListner{
        void onItemClick(History history);
    }
    OnItemClickListner listner;
    public static class ViewHolder extends RecyclerView.ViewHolder{
    private   TextView Historyname;
    private   TextView Historyquan;
    private   TextView Historyprice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Historyname = itemView.findViewById(R.id.History_name);
            Historyquan = itemView.findViewById(R.id.History_quan);
            Historyprice = itemView.findViewById(R.id.History_product_price);
        }
            public TextView getHistoryname(){
                return Historyname;
            }
            public TextView getHistoryquan(){
                return Historyquan;
            }
            public TextView getHistoryprice(){
                return Historyprice;
            }

        }

    public HistoryRecycleAdapter(Context c,ArrayList<History>histories,OnItemClickListner listner) {
        context = c;
        HistoryList1 = histories;
        this.listner=listner;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerrow_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryRecycleAdapter.ViewHolder holder,int position) {
     holder.getHistoryname().setText(HistoryList1.get(position).getName());
     holder.getHistoryquan().setText(String.valueOf(HistoryList1.get(position).getQuantity()));
     holder.getHistoryprice().setText(String.valueOf(HistoryList1.get(position).getPrice()));
     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             listner.onItemClick(HistoryList1.get(position));
         }
     });
    }
    @Override
    public int getItemCount() {
        return HistoryList1.size();
    }
}
