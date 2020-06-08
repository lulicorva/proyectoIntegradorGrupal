package com.example.proyectointegradorgrupal.dao;

import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.model.AlbumContainer;
import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.service.AlbumService;
import com.example.proyectointegradorgrupal.util.ResultListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumDao extends RetrofitDao {

    private AlbumService albumService;

    public AlbumDao() {
        albumService = super.retrofit.create(AlbumService.class);
    }

    //Este metodo devuelve un contenedor de albums con los que coinciden con el search
    public void getAlbumPorSearch(String search, final ResultListener<List<Album>> resultListenerPorController) {
        Call<AlbumContainer> call = this.albumService.getAlbumPorSearch(search);

        call.enqueue(new Callback<AlbumContainer>() {
            @Override
            public void onResponse(Call<AlbumContainer> call, Response<AlbumContainer> response) {
                if (response.isSuccessful()) {
                    AlbumContainer albumContainer = response.body();
                    resultListenerPorController.onFinish(albumContainer.getAlbums());

                } else {
                    response.errorBody();
                }
            }

            @Override
            public void onFailure(Call<AlbumContainer> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    //Este metodo es para consultar un solo album (cuando alguien hace click acceder a la info)
    public void getAlbumById(String id, final ResultListener<Album> resultListenerFromController) {
        Call<Album> call = this.albumService.getAlbumById(id);
        call.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                if (response.isSuccessful()) {
                    Album album = response.body();
                    resultListenerFromController.onFinish(album);
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

    public void getAlbumTracks(String id, final ResultListener<Track> objectResultListenerFromController) {
        Call<Track> call = this.albumService.getAlbumTracks(id);
        call.enqueue(new Callback<Track>() {
            @Override
            public void onResponse(Call<Track> call, Response<Track> response) {
                if (response.isSuccessful()) {
                    Track body = response.body();
                    objectResultListenerFromController.onFinish(body);
                } else {
                    response.errorBody();
                }
            }

            @Override
            public void onFailure(Call<Track> call, Throwable t) {
                t.printStackTrace();
            }

        });
    }

}
