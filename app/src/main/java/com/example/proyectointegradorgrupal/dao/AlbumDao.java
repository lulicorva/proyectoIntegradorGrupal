package com.example.proyectointegradorgrupal.dao;

import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.service.AlbumService;
import com.example.proyectointegradorgrupal.util.ResultListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumDao extends RetrofitDao {

    private AlbumService albumService;

    public AlbumDao() {
        albumService = super.retrofit.create(AlbumService.class);
    }

    public void getAlbum(ResultListener<Album> resultListenerPorController) {
        Call<Album> call = this.albumService.getAlbumById("103248");

        call.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                if (response.isSuccessful()) {
                    Object body = response.body();

                } else {
                    response.errorBody();
                }
            }

            @Override
            public void onFailure(Call<Album> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
