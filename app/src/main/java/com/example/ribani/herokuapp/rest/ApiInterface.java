package com.example.ribani.herokuapp.rest;

import com.example.ribani.herokuapp.model.ResponseItem;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("actors")
    Call<ResponseItem> getActors();
}
