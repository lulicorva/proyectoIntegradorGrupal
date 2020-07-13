package com.example.proyectointegradorgrupal.service;

import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.model.Track;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


//ESTA CLASE NO SE USO PARA NADA TODAVIA
public interface TrackService {

    //Metodo para consultar 1 solo album
    @GET("track/{id}")
    Call<Track> getTrackById(@Path("id") String id);


}
