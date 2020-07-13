package com.example.proyectointegradorgrupal.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "playlistsFavoritos")
public class Playlist implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private int idROOM;


    private String id;

    public int getIdROOM() {
        return idROOM;
    }

    public void setIdROOM(int idROOM) {
        this.idROOM = idROOM;
    }

    @ColumnInfo(name = "title")
    private String title;
    private String picture;

    @Ignore
    private Artist playlistArtist;

    @SerializedName("picture_xl")
    private String pictureXL;

    public Playlist() {
    }

    @Ignore
    public Playlist(String id, String title, String picture, Artist playlistArtist, String pictureXL) {
        this.id = id;
        this.title = title;
        this.picture = picture;
        this.playlistArtist = playlistArtist;
        this.pictureXL = pictureXL;
    }

    public String getPictureXL() {
        return pictureXL;
    }

    public void setPictureXL(String pictureXL) {
        this.pictureXL = pictureXL;
    }

    public Artist getPlaylistArtist() {
        return playlistArtist;
    }

    public void setPlaylistArtist(Artist playlistArtist) {
        this.playlistArtist = playlistArtist;
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

