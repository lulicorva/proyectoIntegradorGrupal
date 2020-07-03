package com.example.proyectointegradorgrupal.dao;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetrofitPodcastDao {

    private static final String URL_BASE = "https://listen-api.listennotes.com/api/v2/";
    protected Retrofit retrofit;

    public RetrofitPodcastDao() {

        retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public static String getUrlBase() {
        return URL_BASE;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
