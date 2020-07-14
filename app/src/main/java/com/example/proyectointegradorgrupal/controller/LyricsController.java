package com.example.proyectointegradorgrupal.controller;

import com.example.proyectointegradorgrupal.dao.LyricsDao;
import com.example.proyectointegradorgrupal.model.Body;
import com.example.proyectointegradorgrupal.model.Example;
import com.example.proyectointegradorgrupal.model.Message;
import com.example.proyectointegradorgrupal.util.ResultListener;

public class LyricsController {

    private LyricsDao lyricsDao;

    public LyricsController() {
        this.lyricsDao = new LyricsDao();
    }

    public void getLyrics(String trackName, String artistName, final ResultListener<Example> resultListenerFromView) {
        lyricsDao.getLyrics(trackName, artistName, new ResultListener<Example>() {
            @Override
            public void onFinish(Example result) {
            resultListenerFromView.onFinish(result);
            }
        });
    }


}
