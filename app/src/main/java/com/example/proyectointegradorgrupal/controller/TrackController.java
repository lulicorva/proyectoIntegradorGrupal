package com.example.proyectointegradorgrupal.controller;

import com.example.proyectointegradorgrupal.dao.AlbumDao;
import com.example.proyectointegradorgrupal.dao.TrackDao;
import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.util.ResultListener;

public class TrackController {

    private TrackDao trackDao;

    public TrackController() {
        this.trackDao = new TrackDao();
    }


    public void getTrackById(String id, final ResultListener<Track> resultListenerFromView){
        trackDao.getTrackById(id, new ResultListener<Track>() {
            @Override
            public void onFinish(Track result) {
                resultListenerFromView.onFinish(result);
            }
        });


    }

}
