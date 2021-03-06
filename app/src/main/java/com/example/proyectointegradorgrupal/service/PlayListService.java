package com.example.proyectointegradorgrupal.service;

import com.example.proyectointegradorgrupal.model.ConteinerPlayList;
import com.example.proyectointegradorgrupal.model.Playlist;
import com.example.proyectointegradorgrupal.model.Track;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PlayListService {

    @GET("search/playlist")
    Call<ConteinerPlayList> getPlaylistporSearch (@Query("q") String genero);

    //Construla los tracks de la playlist por ID de la playlist
    @GET("playlist/{id}/tracks")
    Call<Track> getPlaylistTracks(@Path("id") String id);



}
