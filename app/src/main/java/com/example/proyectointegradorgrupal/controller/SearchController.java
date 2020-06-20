package com.example.proyectointegradorgrupal.controller;

import com.example.proyectointegradorgrupal.dao.SearchDao;
import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.model.SearchContainer;
import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.util.ResultListener;

import java.util.List;

public class SearchController {

    private SearchDao searchDao;

    public SearchController() {
        this.searchDao = new SearchDao();
    }

    public void getSearch(String query, final ResultListener<Track> resultListenerFromView){
        searchDao.getSearch(query, new ResultListener<Track>() {
            @Override
            public void onFinish(Track result) {
                resultListenerFromView.onFinish(result);
            }
        });


    }

}
