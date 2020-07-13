package com.example.proyectointegradorgrupal.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Lyrics implements Serializable {

    @SerializedName("lyrics_body")
    private String lyricsBody;

    public Lyrics(String lyricsBody) {
        this.lyricsBody = lyricsBody;
    }

    public String getLyricsBody() {
        return lyricsBody;
    }

    public void setLyricsBody(String lyricsBody) {
        this.lyricsBody = lyricsBody;
    }
}

