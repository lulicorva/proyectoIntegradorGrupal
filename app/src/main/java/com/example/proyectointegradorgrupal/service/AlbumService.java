package com.example.proyectointegradorgrupal.service;

import com.example.proyectointegradorgrupal.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AlbumService {

    //Metodo para consultar 1 solo album
    @GET("album/{id}")
    Call<Album> getAlbumById(@Path("id") String id);

    //Este metodo creo que no sirve porque la api de deezer no trae container de album
    @GET("album")
    Call<Object> getAlbum();

}
