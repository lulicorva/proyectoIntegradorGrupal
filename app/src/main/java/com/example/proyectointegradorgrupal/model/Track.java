package com.example.proyectointegradorgrupal.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Track {

    @SerializedName("data")
    private ArrayList<Track> data;
    private String id;
    private String title;
    private Double duration;
    private Double trackPosition;
    private String preview;

    public ArrayList<Track> getData() {
        return data;
    }

    public void setTrackList(ArrayList<Track> data) {
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

    public Track(ArrayList<Track> data, String id, String title, Double duration, Double trackPosition, String preview) {
        this.data = data;
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.trackPosition = trackPosition;
        this.preview = preview;
    }

    public Track() {
    }
}
