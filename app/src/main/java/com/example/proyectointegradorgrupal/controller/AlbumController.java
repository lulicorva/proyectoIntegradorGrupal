package com.example.proyectointegradorgrupal.controller;

import com.example.proyectointegradorgrupal.dao.AlbumDao;
import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.model.AlbumContainer;
import com.example.proyectointegradorgrupal.util.ResultListener;

import java.util.List;

public class AlbumController {

    private AlbumDao albumDao;

    public AlbumController() {
        this.albumDao = new AlbumDao();
    }

    public void getAlbumById(final ResultListener<Album> resultListenerFromView) {
        albumDao.getAlbumById(new ResultListener<Album>() {
            @Override
            public void onFinish(Album result) {

            }
        });
    }

    public void getAlbumPorSearch(final ResultListener<List<Album>> resultListenerFromView) {
        albumDao.getAlbumPorSearch(new ResultListener<List<Album>>() {
            @Override
            public void onFinish(List<Album> result) {
                resultListenerFromView.onFinish(result);
            }
        });
    }
}





