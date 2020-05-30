package com.example.proyectointegradorgrupal.dao;

import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.model.AlbumContainer;
import com.example.proyectointegradorgrupal.service.AlbumService;
import com.example.proyectointegradorgrupal.util.ResultListener;

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
    public void getAlbumPorSearch(final ResultListener<List<Album>> resultListenerPorController) {
        Call<AlbumContainer> call = this.albumService.getAlbumPorSearch("Beatles");

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
    public void getAlbumById(final ResultListener<Album> resultListenerFromController) {
        Call<Album> call = this.albumService.getAlbumById("302127");
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
}
