package com.example.sparker0i.samplenodeapp.api;

import com.example.sparker0i.samplenodeapp.model.PhoneList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/get-items")
    Call<PhoneList> doGetListResources();
}
