package com.example.proyectointegradorgrupal.controller;

import com.example.proyectointegradorgrupal.dao.AlbumDao;
import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.model.AlbumContainer;
import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.util.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class AlbumController {

    private AlbumDao albumDao;

    public AlbumController() {
        this.albumDao = new AlbumDao();
    }

    public void getAlbumById(String id, final ResultListener<Album> resultListenerFromView) {
        albumDao.getAlbumById(id, new ResultListener<Album>() {
            @Override
            public void onFinish(Album result) {
                resultListenerFromView.onFinish(result);
            }
        });
    }

    public void getAlbumPorSearch(String search, final ResultListener<List<Album>> resultListenerFromView) {
        albumDao.getAlbumPorSearch(search, new ResultListener<List<Album>>() {
            @Override
            public void onFinish(List<Album> result) {
                resultListenerFromView.onFinish(result);
            }
        });
    }

    public void getAlbumTracks(String id, final ResultListener<Track> resultListenerFromView) {
        albumDao.getAlbumTracks(id, new ResultListener<Track>() {
            @Override
            public void onFinish(Track result) {
                resultListenerFromView.onFinish(result);
            }
        });
    }
}





