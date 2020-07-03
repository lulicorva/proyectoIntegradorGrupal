package com.example.proyectointegradorgrupal.controller;

import com.example.proyectointegradorgrupal.dao.PodcastDao;
import com.example.proyectointegradorgrupal.model.PodcastContainer;
import com.example.proyectointegradorgrupal.util.ResultListener;

public class PodcastController {

    private PodcastDao podcastDao;

    public PodcastController(){
        this.podcastDao = new PodcastDao();
    }

    public void getPodcastRecomendados(String id, final ResultListener<PodcastContainer> podcastContainerResultListener) {
        this.podcastDao.getPodcastContainerById(id, new ResultListener<PodcastContainer>() {
            @Override
            public void onFinish(PodcastContainer result){
                podcastContainerResultListener.onFinish(result);
            }

        });
    }
}
