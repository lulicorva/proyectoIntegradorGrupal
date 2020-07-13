package com.example.proyectointegradorgrupal.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public abstract class RetrofitPodcastDao {

    private static final String URL_BASE = "https://listen-api.listennotes.com/";
    protected Retrofit retrofit;

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public RetrofitPodcastDao() {

        retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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
