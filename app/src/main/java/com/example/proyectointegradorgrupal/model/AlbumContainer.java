package com.example.proyectointegradorgrupal.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlbumContainer {


    @SerializedName("data")
    private List<Album> albums;

    private Double total;
    private String nextPage;

    public AlbumContainer() {

    }

    public AlbumContainer(List<Album> albums, Double total, String nextPage) {
        this.albums = albums;
        this.total = total;
        this.nextPage = nextPage;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }
}
