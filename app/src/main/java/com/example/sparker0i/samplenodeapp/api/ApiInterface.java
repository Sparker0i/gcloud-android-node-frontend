package com.example.sparker0i.samplenodeapp.api;

import com.example.sparker0i.samplenodeapp.model.Phone;
import com.example.sparker0i.samplenodeapp.model.Sales;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/get-items")
    Call<List<Phone>> doGetPhones();

    @GET("/get-items?")
    Call<List<Phone>> doGetPhonesWithQuery(
            @Query("manufacturer") String manufacturer,
            @Query("model") String model,
            @Query("min-price") Integer minPrice,
            @Query("max-price") Integer maxPrice);

    @GET("/getSalesRecords")
    Call<List<Sales>> getSalesRecords();

    @GET("/buy?")
    Call<Sales> buyPhone(
            @Query("model") String model,
            @Query("username") String username,
            @Query("qty") Integer quantity
    );
}
