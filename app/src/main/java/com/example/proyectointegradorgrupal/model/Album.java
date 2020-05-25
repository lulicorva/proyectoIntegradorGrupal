package com.example.proyectointegradorgrupal.model;

public class Album {
    private Integer id;
    private String title;
    private String link;
    private String cover;
    private Integer duration;
    private Integer rating;
    private String tracklist;

    public Album() {

    }

    public Album(Integer id, String title, String link, String cover, Integer duration, Integer rating, String tracklist) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.cover = cover;
        this.duration = duration;
        this.rating = rating;
        this.tracklist = tracklist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }
}
