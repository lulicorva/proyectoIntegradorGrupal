package com.example.proyectointegradorgrupal.controller;

import com.example.proyectointegradorgrupal.dao.AlbumDao;
import com.example.proyectointegradorgrupal.dao.PlaylistDao;
import com.example.proyectointegradorgrupal.model.Playlist;
import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.util.ResultListener;

import java.util.List;

public class PlaylistController {

    private PlaylistDao playlistDao;

    public PlaylistController() { this.playlistDao = new PlaylistDao(); }

            public void getPlaylistPorSearch(String playlist, final ResultListener<List<Playlist>> resultListenerFromView) {
                playlistDao.getPlaylistPorSearch(playlist, new ResultListener<List<Playlist>>() {
                    @Override
                    public void onFinish(List<Playlist> result) {
                        resultListenerFromView.onFinish(result);
                    }
                });


            }


            public void getPlaylistTracks(String playlist, final ResultListener<Track> resultListenerFromView){
        playlistDao.getPlaylistTracks(playlist, new ResultListener<Track>() {
            @Override
            public void onFinish(Track result) {
                resultListenerFromView.onFinish(result);
            }
        });

            }

        }


