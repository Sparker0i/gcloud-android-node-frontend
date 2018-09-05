package com.example.sparker0i.samplenodeapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.sparker0i.samplenodeapp.R;
import com.example.sparker0i.samplenodeapp.api.ApiClient;
import com.example.sparker0i.samplenodeapp.api.ApiInterface;
import com.example.sparker0i.samplenodeapp.model.Sales;
import com.example.sparker0i.samplenodeapp.results.SalesAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ApiInterface apiInterface;
    List<Sales> sales;
    SalesAdapter adapter;
    MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        dialog = new MaterialDialog(this)
                .title(R.string.please_wait , "Please Wait")
                .message(R.string.loading , "Loading");
        dialog.show();

        Call<List<Sales>> call = apiInterface.getSalesRecords();
        call.enqueue(new Callback<List<Sales>>() {
            @Override
            public void onResponse(Call<List<Sales>> call, Response<List<Sales>> response) {
                sales = response.body();
                renderSales();
            }

            @Override
            public void onFailure(Call<List<Sales>> call, Throwable t) {
                call.cancel();
                t.printStackTrace();
            }
        });
    }

    private void renderSales() {
        adapter = new SalesAdapter(this , sales);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        dialog.dismiss();
    }
}
