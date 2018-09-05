package com.example.sparker0i.samplenodeapp.results;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sparker0i.samplenodeapp.R;
import com.example.sparker0i.samplenodeapp.model.Sales;

import java.util.List;

public class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.MyViewHolder> {

    private Context context;
    private List<Sales> sales;

    public SalesAdapter(Context context , List<Sales> sales) {
        this.context = context;
        this.sales = sales;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.sales , viewGroup , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Sales sale = sales.get(position);
        holder.quantity.setText("Quantity: " + sale.quantity);
        holder.invoiceNumber.setText("Invoice Number: " + sale.invoiceNumber);
        holder.model.setText("Model: " + sale.model);
    }

    @Override
    public int getItemCount() {
        return sales.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView model , invoiceNumber , quantity;

        public MyViewHolder(@NonNull View view) {
            super(view);
            model = view.findViewById(R.id.model);
            invoiceNumber = view.findViewById(R.id.invoiceNumber);
            quantity = view.findViewById(R.id.quantity);
        }
    }
}
