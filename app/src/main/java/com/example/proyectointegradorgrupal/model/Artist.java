package com.example.proyectointegradorgrupal.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Artist implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("tracklist")
    private String trackListURL;

    @SerializedName("picture_big")
    private String pictureBigURL;

    public Artist() {
    }

    public Artist(String id, String name, String trackListURL, String pictureBigURL) {
        this.id = id;
        this.name = name;
        this.trackListURL = trackListURL;
        this.pictureBigURL = pictureBigURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrackListURL() {
        return trackListURL;
    }

    public void setTrackListURL(String trackListURL) {
        this.trackListURL = trackListURL;
    }

    public String getPictureBigURL() {
        return pictureBigURL;
    }

    public void setPictureBigURL(String pictureBigURL) {
        this.pictureBigURL = pictureBigURL;
    }
}
