package com.example.proyectointegradorgrupal.model;

import java.io.Serializable;
import java.util.List;

public class DatosUsuario implements Serializable {
    private List<Track> tracksFavoritos;
    private List<Album> albumesFavoritos;
    private List<Artist> artistasFavoritos;
    private List<Playlist> playlistsFavoritos;


    public DatosUsuario() {
    }

    public DatosUsuario(List<Track> tracksFavoritos, List<Album> albumesFavoritos, List<Artist> artistasFavoritos, List<Playlist> playlistsFavoritos) {
        this.tracksFavoritos = tracksFavoritos;
        this.albumesFavoritos = albumesFavoritos;
        this.artistasFavoritos = artistasFavoritos;
        this.playlistsFavoritos = playlistsFavoritos;
    }

    public List<Track> getTracksFavoritos() {
        return tracksFavoritos;
    }

    public void setTracksFavoritos(List<Track> tracksFavoritos) {
        this.tracksFavoritos = tracksFavoritos;
    }

    public List<Album> getAlbumesFavoritos() {
        return albumesFavoritos;
    }

    public void setAlbumesFavoritos(List<Album> albumesFavoritos) {
        this.albumesFavoritos = albumesFavoritos;
    }

    public List<Artist> getArtistasFavoritos() {
        return artistasFavoritos;
    }

    public void setArtistasFavoritos(List<Artist> artistasFavoritos) {
        this.artistasFavoritos = artistasFavoritos;
    }

    public List<Playlist> getPlaylistsFavoritos() {
        return playlistsFavoritos;
    }

    public void setPlaylistsFavoritos(List<Playlist> playlistsFavoritos) {
        this.playlistsFavoritos = playlistsFavoritos;
    }
}
