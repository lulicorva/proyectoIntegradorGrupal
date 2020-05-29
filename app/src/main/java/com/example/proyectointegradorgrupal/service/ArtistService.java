package com.example.proyectointegradorgrupal.service;

import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.model.AlbumContainer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ArtistService {

    //Metodo para consultar 1 solo artista por ID
    @GET("artist/{id}")
    Call<Object> getArtistById(@Path("id") String id);


    //Consultar un search dentro de artists
    @GET("search/artist")
    Call<Object> getArtistPorSearch(@Query("q") String artist);
}
