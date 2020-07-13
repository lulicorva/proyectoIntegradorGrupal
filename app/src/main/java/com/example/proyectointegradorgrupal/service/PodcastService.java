package com.example.proyectointegradorgrupal.service;

import com.example.proyectointegradorgrupal.model.PodcastContainer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface PodcastService {

    @Headers("X-ListenAPI-Key: b7c81fb692244b2b8ad37a40eab6f228")
    @GET("api/v2/episodes/{id}/recommendations?safe_mode=0")
    Call<PodcastContainer> getPodcast(@Path("id") String search);
}
