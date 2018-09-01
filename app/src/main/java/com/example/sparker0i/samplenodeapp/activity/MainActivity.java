package com.example.sparker0i.samplenodeapp.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.sparker0i.samplenodeapp.R;
import com.example.sparker0i.samplenodeapp.api.ApiClient;
import com.example.sparker0i.samplenodeapp.api.ApiInterface;
import com.example.sparker0i.samplenodeapp.model.Phone;
import com.example.sparker0i.samplenodeapp.model.PhoneList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<List<Phone>> call = apiInterface.doGetPhones();
        call.enqueue(new Callback<List<Phone>>() {
            @Override
            public void onResponse(Call<List<Phone>> call, Response<List<Phone>> response) {
                List<Phone> list = response.body();
                if (list != null) {
                    for (Phone phone : list) {
                        Log.i("Phone Details : " , phone.getManufacturer() + " " + phone.getModel() + " " + phone.getPrice() + " " + phone.getQuantity());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Phone>> call, Throwable t) {
                call.cancel();
                t.printStackTrace();
            }
        });
    }
}
