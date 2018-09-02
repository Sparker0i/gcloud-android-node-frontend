package com.example.sparker0i.samplenodeapp.results;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sparker0i.samplenodeapp.R;
import com.example.sparker0i.samplenodeapp.model.Phone;

import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.MyViewHolder> {

    private Context context;
    private List<Phone> phones;

    public PhoneAdapter(Context context , List<Phone> phones) {
        this.context = context;
        this.phones = phones;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.card , viewGroup , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Phone phone = phones.get(i);
        myViewHolder.price.setText(phone.getPrice() + "");
        myViewHolder.manufacturer.setText(phone.getManufacturer());
        myViewHolder.model.setText(phone.getModel());
        Glide.with(context).load(phone.getImage()).into(myViewHolder.image);
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView model , manufacturer , price;
        ImageView image;


        public MyViewHolder(@NonNull View view) {
            super(view);
            model = view.findViewById(R.id.model);
            manufacturer = view.findViewById(R.id.manufacturer);
            price = view.findViewById(R.id.price);
            image = view.findViewById(R.id.image);
        }
    }
}
