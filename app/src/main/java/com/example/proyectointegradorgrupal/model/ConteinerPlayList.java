package com.example.proyectointegradorgrupal.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class ConteinerPlayList {

    @SerializedName("data")
    public  List<Playlist> listaDePlaylist;

    public ConteinerPlayList() {
    }

    public List<Playlist> getListaDePlaylist() {
        return listaDePlaylist;
    }

    public void setListaDePlaylist(List<Playlist> listaDePlaylist) {
        this.listaDePlaylist = listaDePlaylist;
    }
}
