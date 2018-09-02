package com.example.sparker0i.samplenodeapp.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.sparker0i.samplenodeapp.R;
import com.example.sparker0i.samplenodeapp.api.ApiClient;
import com.example.sparker0i.samplenodeapp.api.ApiInterface;
import com.example.sparker0i.samplenodeapp.model.Phone;
import com.example.sparker0i.samplenodeapp.results.PhoneAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    List<Phone> phones = new ArrayList<>();
    RecyclerView recyclerView;
    PhoneAdapter adapter;
    MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();

        recyclerView = findViewById(R.id.recycler_view);

        dialog = new MaterialDialog(this)
                .title(R.string.please_wait , "Please Wait")
                .message(R.string.loading , "Loading");
        dialog.show();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this , 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2 , dpToPx() , true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<List<Phone>> call;
        if (bundle == null || (bundle.getString("manufacturer") == null && bundle.getString("model") == null && bundle.getInt("min-price") == -1 && bundle.getInt("max-price") == -1))
            call = apiInterface.doGetPhones();
        else {
            String manufacturer , model;
            Integer min = bundle.getInt("min-price"), max = bundle.getInt("max-price");
            try {
                manufacturer = bundle.getString("manufacturer");
            }
            catch (NullPointerException ex) {
                manufacturer = null;
            }
            try {
                model = bundle.getString("model");
            }
            catch (NullPointerException ex) {
                model = null;
            }
            if (min == -1)
                min = null;
            if (max == -1)
                max = null;
            call = apiInterface.doGetPhonesWithQuery(manufacturer , model , min , max);
        }

        call.enqueue(new Callback<List<Phone>>() {
            @Override
            public void onResponse(@NonNull Call<List<Phone>> call, @NonNull Response<List<Phone>> response) {
                phones = response.body();
                if (phones != null) {
                    for (Phone phone : phones) {
                        Log.i("Phone Details : " , phone.getManufacturer() + " " + phone.getModel() + " " + phone.getPrice() + " " + phone.getQuantity());
                    }
                    renderPhones();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Phone>> call, @NonNull Throwable t) {
                call.cancel();
                t.printStackTrace();
            }
        });

        /*call = apiInterface.doGetPhonesWithQuery("Xiaomi" , null , 5000 , 50000);
        call.enqueue(new Callback<List<Phone>>() {
            @Override
            public void onResponse(@NonNull Call<List<Phone>> call, @NonNull Response<List<Phone>> response) {
                phones = response.body();
                if (phones != null) {
                    for (Phone phone : phones) {
                        Log.i("Phone Details Pro : " , phone.getManufacturer() + " " + phone.getModel() + " " + phone.getPrice() + " " + phone.getQuantity());
                    }
                    renderPhones();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Phone>> call, @NonNull Throwable t) {
                call.cancel();
                t.printStackTrace();
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                startActivity(new Intent(this , SearchActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void renderPhones() {
        adapter = new PhoneAdapter(this , phones);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        dialog.dismiss();
    }

    private int dpToPx() {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, r.getDisplayMetrics()));
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing;
                }
            }
        }
    }
}
