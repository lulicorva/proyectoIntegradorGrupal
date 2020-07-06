package com.example.proyectointegradorgrupal.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


@Entity(tableName = "tracksFavoritos")
public class Track implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int idROOM;

    public int getIdROOM() {
        return idROOM;
    }

    public void setIdROOM(int idROOM) {
        this.idROOM = idROOM;
    }

    @SerializedName("data")
    @Ignore
    private List<Track> data;
    private String id;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String title;

    @ColumnInfo(name = "duration")
    private Double duration;
    private Double trackPosition;

    @ColumnInfo(name = "preview")
    private String preview;


    @SerializedName("album")
    @Ignore
    private Album album;

    @SerializedName("title_short")
    private String titleShort;

    @SerializedName("artist")
    @Ignore
    private Artist artist;

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getTitleShort() {
        return titleShort;
    }

    public void setTitleShort(String titleShort) {
        this.titleShort = titleShort;
    }

    public List<Track> getData() {
        return data;
    }

    public void setTrackList(List<Track> data) {
        this.data = data;
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

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Double getTrackPosition() {
        return trackPosition;
    }

    public void setTrackPosition(Double trackPosition) {
        this.trackPosition = trackPosition;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public void setData(List<Track> data) {
        this.data = data;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Ignore
    public Track(List<Track> data, String id, String title, Double duration, Double trackPosition, String preview, Album album, String titleShort, Artist artist) {
        this.data = data;
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.trackPosition = trackPosition;
        this.preview = preview;
        this.album = album;
        this.titleShort = titleShort;
        this.artist = artist;
    }

    public Track() {
    }
}
