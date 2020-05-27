package com.example.proyectointegradorgrupal.model;

public class Playlist {

    private String id;
    private String title;
    private String picture;

    public Playlist() {
    }

    public Playlist(String id, String title, String picture) {
        this.id = id;
        this.title = title;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String Picture) {
        this.picture = picture;
    }
}

