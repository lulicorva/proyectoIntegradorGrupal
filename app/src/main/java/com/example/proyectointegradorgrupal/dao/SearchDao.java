package com.example.proyectointegradorgrupal.dao;

import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.service.SearhService;
import com.example.proyectointegradorgrupal.util.ResultListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchDao extends RetrofitDao {
    private SearhService searhService;

    public SearchDao() {
        searhService = super.retrofit.create(SearhService.class);
    }


    public void getSearch(String search, final ResultListener<Track> resultListenerPorController){
        Call<Track> call = this.searhService.getSearch(search);

        call.enqueue(new Callback<Track>() {
            @Override
            public void onResponse(Call<Track> call, Response<Track> response) {
                Track object = response.body();
                resultListenerPorController.onFinish(object);

            }

            @Override
            public void onFailure(Call<Track> call, Throwable t) {

            }
        });


    }

}
