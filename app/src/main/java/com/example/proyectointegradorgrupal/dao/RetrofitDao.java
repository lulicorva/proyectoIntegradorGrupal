package com.example.proyectointegradorgrupal.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetrofitDao {

    private static final String URL_BASE = "https://api.deezer.com/";
    protected Retrofit retrofit;

    public RetrofitDao() {


        retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }
}
