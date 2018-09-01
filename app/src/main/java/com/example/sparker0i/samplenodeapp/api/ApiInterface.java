package com.example.sparker0i.samplenodeapp.api;

import com.example.sparker0i.samplenodeapp.model.Phone;
import com.example.sparker0i.samplenodeapp.model.PhoneList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/get-items")
    Call<List<Phone>> doGetPhones();

    @GET("/get-items?")
    Call<PhoneList> doGetPhonesWithQuery(
            @Query("manufacturer") String manufacturer,
            @Query("model") String model,
            @Query("min-price") Integer minPrice,
            @Query("max-price") Integer maxPrice);
}
