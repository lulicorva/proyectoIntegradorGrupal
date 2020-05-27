package com.example.proyectointegradorgrupal.dao;

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.model.ConteinerPlayList;
import com.example.proyectointegradorgrupal.model.Playlist;
import com.example.proyectointegradorgrupal.service.PlayListService;
import com.example.proyectointegradorgrupal.util.ResultListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistDao extends RetrofitDao{



private PlayListService playListService;

public PlaylistDao(){playListService = retrofit.create(PlayListService.class);}

public void getPlaylistPorSearch(final ResultListener<List<Playlist>> resultListenerPorController){
    Call<ConteinerPlayList> call = this.playListService.getPlaylistporSearch("Regaetton");
    call.enqueue(new Callback<ConteinerPlayList>() {
        @Override
        public void onResponse(Call<ConteinerPlayList> call, Response<ConteinerPlayList> response) {
            if(response.isSuccessful()) {
                ConteinerPlayList conteinerPlayList = response.body();
                resultListenerPorController.onFinish(conteinerPlayList.getListaDePlaylist());

            }else{
                response.errorBody();
            }
        }

        @Override
        public void onFailure(Call<ConteinerPlayList> call, Throwable t) {
        t.printStackTrace();
        }
    });
}

}





