package com.example.proyectointegradorgrupal.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Podcast implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("link")
    private String link;
    @SerializedName("audio")
    private String audio;
    @SerializedName("image")
    private String image;
    @SerializedName("podcast_image")
    private String podcastTitle;
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("podcast_id")
    private String podcastId;
    @SerializedName("listennotes_url")
    private String listennotesUrl;
    @SerializedName("audio_length_sec")
    private Integer audioLength;

    public Podcast() {
    }

    public Podcast(String id, String link, String audio, String image, String podcastTitle, String thumbnail, String podcastId, String listennotesUrl, Integer audioLength) {
        this.id = id;
        this.link = link;
        this.audio = audio;
        this.image = image;
        this.podcastTitle = podcastTitle;
        this.thumbnail = thumbnail;
        this.podcastId = podcastId;
        this.listennotesUrl = listennotesUrl;
        this.audioLength = audioLength;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPodcastTitle(String podcastTitle) {
        this.podcastTitle = podcastTitle;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setPodcastId(String podcastId) {
        this.podcastId = podcastId;
    }

    public void setListennotesUrl(String listennotesUrl) {
        this.listennotesUrl = listennotesUrl;
    }

    public void setAudioLength(Integer audioLength) {
        this.audioLength = audioLength;
    }

    public String getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public String getAudio() {
        return audio;
    }

    public String getImage() {
        return image;
    }

    public String getPodcastTitle() {
        return podcastTitle;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getPodcastId() {
        return podcastId;
    }

    public String getListennotesUrl() {
        return listennotesUrl;
    }

    public Integer getAudioLength() {
        return audioLength;
    }
}
