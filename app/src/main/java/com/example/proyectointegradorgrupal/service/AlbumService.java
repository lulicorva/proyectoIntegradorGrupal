package com.example.proyectointegradorgrupal.service;

import com.example.proyectointegradorgrupal.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AlbumService {

    //Metodo para consultar 1 solo album
    @GET("album/{id}")
    Call<Album> getAlbum(@Path("id") String id);

    @GET("album")
    Call<Object> getAlbum();

}
