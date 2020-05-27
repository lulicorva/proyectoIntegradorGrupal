package com.example.proyectointegradorgrupal.controller;

import com.example.proyectointegradorgrupal.dao.AlbumDao;
import com.example.proyectointegradorgrupal.dao.PlaylistDao;
import com.example.proyectointegradorgrupal.model.Playlist;
import com.example.proyectointegradorgrupal.util.ResultListener;

import java.util.List;

public class PlaylistController {

    private PlaylistDao playlistDao;

    public PlaylistController() { this.playlistDao = new PlaylistDao(); }

            public void getPlaylistPorSearch(final ResultListener<List<Playlist>> resultListenerFromView) {
                playlistDao.getPlaylistPorSearch(new ResultListener<List<Playlist>>() {
                    @Override
                    public void onFinish(List<Playlist> result) {
                        resultListenerFromView.onFinish(result);
                    }
                });


            }
        }


