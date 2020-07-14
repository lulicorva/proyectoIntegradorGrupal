package com.example.proyectointegradorgrupal.service;

import com.example.proyectointegradorgrupal.model.Body;
import com.example.proyectointegradorgrupal.model.Example;
import com.example.proyectointegradorgrupal.model.Lyrics;
import com.example.proyectointegradorgrupal.model.Message;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LyricService {

    //Consultar una letra por nombre de Canción y de Artista
    @GET("matcher.lyrics.get")
    Call<Example> getLyrics(@Query("q_track") String trackName, @Query("q_artist") String artistName, @Query("apikey") String apikey);
}




