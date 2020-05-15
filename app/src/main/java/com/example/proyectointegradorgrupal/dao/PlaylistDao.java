package com.example.proyectointegradorgrupal.dao;

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.model.Playlist;

import java.util.ArrayList;
import java.util.List;

public abstract class PlaylistDao {

    public static List<Playlist> getPlaylist() {
        List<Playlist> playlistList = new ArrayList<>();

        playlistList.add(new Playlist("Tecno", R.drawable.playlist_a));
        playlistList.add(new Playlist("Bailable", R.drawable.playlist_b));
        playlistList.add(new Playlist("Rock nacional",R.drawable.playlist_c));
        playlistList.add(new Playlist("Relax",R.drawable.playlist_d));


        return playlistList;


    }

}
