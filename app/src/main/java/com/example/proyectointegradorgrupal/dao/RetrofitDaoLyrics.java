package com.example.proyectointegradorgrupal.dao;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetrofitDaoLyrics {

    private static final String URL_BASE = "https://api.musixmatch.com/ws/1.1/";
    protected Retrofit retrofitLyrics;


    public RetrofitDaoLyrics() {

        retrofitLyrics = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }
}
