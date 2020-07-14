package com.example.proyectointegradorgrupal.dao;

import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.service.AlbumService;
import com.example.proyectointegradorgrupal.service.TrackService;
import com.example.proyectointegradorgrupal.util.ResultListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackDao extends RetrofitDao {

    private TrackService trackService;


    public TrackDao() {
        trackService = super.retrofit.create(TrackService.class);
    }


    public void getTrackById(String id, final ResultListener<Track> resultListenerFromController){
        Call<Track> call = this.trackService.getTrackById(id);

        call.enqueue(new Callback<Track>() {
            @Override
            public void onResponse(Call<Track> call, Response<Track> response) {
                resultListenerFromController.onFinish(response.body());
            }

            @Override
            public void onFailure(Call<Track> call, Throwable t) {

            }
        });


    }

}
