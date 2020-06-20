package com.example.proyectointegradorgrupal.service;

import com.example.proyectointegradorgrupal.model.AlbumContainer;
import com.example.proyectointegradorgrupal.model.SearchContainer;
import com.example.proyectointegradorgrupal.model.Track;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearhService {

    // https://api.deezer.com/search?q=eminem

    @GET("search/")
    Call<Track> getSearch(@Query("q") String track);

}
