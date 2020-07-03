package com.example.proyectointegradorgrupal.dao;

import com.example.proyectointegradorgrupal.model.PodcastContainer;
import com.example.proyectointegradorgrupal.service.PodcastService;
import com.example.proyectointegradorgrupal.util.ResultListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PodcastDao extends RetrofitPodcastDao {

    private PodcastService podcastService;

    public PodcastDao() {
        podcastService = retrofit.create(PodcastService.class);
    }

    public void getPodcastContainerById(String id, final ResultListener<PodcastContainer> resultListenerFromController){
        Call<PodcastContainer>call = this.podcastService.getPodcast(id);
        call.enqueue(new Callback<PodcastContainer>() {
            @Override
            public void onResponse(Call<PodcastContainer> call, Response<PodcastContainer> response) {
                if (response.isSuccessful()) {
                    PodcastContainer contenedorObtenido = response.body();
                    resultListenerFromController.onFinish(contenedorObtenido);
                } else {
                    response.errorBody();
                }
            }

            @Override
            public void onFailure(Call<PodcastContainer> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
