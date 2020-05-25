package com.example.proyectointegradorgrupal.controller;

import com.example.proyectointegradorgrupal.dao.AlbumDao;
import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.util.ResultListener;

public class AlbumController {

    private AlbumDao albumDao;

    public AlbumController() {
        this.albumDao = new AlbumDao();
    }

    public void getAlbum(final ResultListener<Album> resultListenerFromView){
        albumDao.getAlbum(new ResultListener<Album>() {
            @Override
            public void onFinish(Album result) {
                resultListenerFromView.onFinish(result);
            }
        });
    }
}
