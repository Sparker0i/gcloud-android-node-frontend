package com.example.sparker0i.samplenodeapp.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sparker0i.samplenodeapp.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final Context context = this;

        Button button = findViewById(R.id.go);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String manufacturer = ((TextView) findViewById(R.id.manufacturer)).getText().toString();
                if (manufacturer.equals(""))
                    manufacturer = null;
                String model = ((TextView) findViewById(R.id.model)).getText().toString();
                if (model.equals(""))
                    model = null;
                int minprice, maxprice;
                try {
                    minprice = Integer.parseInt(((TextView) findViewById(R.id.minprice)).getText().toString());
                }
                catch (NumberFormatException ex) {
                    minprice = -1;
                }
                try {
                    maxprice = Integer.parseInt(((TextView) findViewById(R.id.maxprice)).getText().toString());
                }
                catch (NumberFormatException ex) {
                    maxprice = -1;
                }
                Intent intent = new Intent(context , MainActivity.class);
                Bundle extras = new Bundle();
                extras.putString("manufacturer" , manufacturer);
                extras.putString("model" , model);
                extras.putInt("min-price" , minprice);
                extras.putInt("max-price" , maxprice);

                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}
