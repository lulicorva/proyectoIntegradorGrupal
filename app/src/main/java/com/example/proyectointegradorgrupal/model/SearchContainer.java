package com.example.proyectointegradorgrupal.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SearchContainer {

    @SerializedName("data")
    private ArrayList<Track> searchContainerList;

    private List<Track> trackList;

    private List<Playlist> playlistList;

    @SerializedName("type")
    private String type;

    public SearchContainer() {
    }


    public SearchContainer(ArrayList<Track> searchContainerList, List<Track> trackList, List<Playlist> playlistList, String type) {
        this.searchContainerList = searchContainerList;
        this.trackList = trackList;
        this.playlistList = playlistList;
        this.type = type;
    }

    public ArrayList<Track> getSearchContainerList() {
        return searchContainerList;
    }

    public void setSearchContainerList(ArrayList<Track> searchContainerList) {
        this.searchContainerList = searchContainerList;
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

    public List<Playlist> getPlaylistList() {
        return playlistList;
    }

    public void setPlaylistList(List<Playlist> playlistList) {
        this.playlistList = playlistList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
