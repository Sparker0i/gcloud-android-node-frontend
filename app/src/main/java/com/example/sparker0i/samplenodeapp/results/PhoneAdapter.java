package com.example.sparker0i.samplenodeapp.results;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
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
import java.util.Locale;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.MyViewHolder> {

    private Context context;
    private List<Phone> phones;
    private OnItemClickListener listener;

    public PhoneAdapter(Context context , List<Phone> phones , OnItemClickListener listener) {
        this.context = context;
        this.phones = phones;
        this.listener = listener;
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
        myViewHolder.bind(phone , listener);
        myViewHolder.price.setText(String.format(Locale.US , "Rs. %d", phone.getPrice()));
        myViewHolder.manufacturer.setText(phone.getManufacturer());
        myViewHolder.model.setText(phone.getModel());
        Glide.with(context).load(phone.getImage()).into(myViewHolder.image);
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView model , manufacturer , price;
        ImageView image;
        CardView cardView;

        MyViewHolder(@NonNull View view) {
            super(view);
            model = view.findViewById(R.id.model);
            cardView = view.findViewById(R.id.card_view);
            manufacturer = view.findViewById(R.id.manufacturer);
            price = view.findViewById(R.id.price);
            image = view.findViewById(R.id.image);
        }

        void bind(final Phone phone, final OnItemClickListener listener) {
            View.OnClickListener lis = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(phone);
                }
            };
            cardView.setOnClickListener(lis);
            image.setOnClickListener(lis);
        }
    }
}
