package com.example.proyectointegradorgrupal.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.proyectointegradorgrupal.dao.DataConverter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "datosUsuario")
public class DatosUsuario implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int idROOM;


    @TypeConverters(DataConverter.class)
    private List<Track> tracksFavoritos;


    @TypeConverters(DataConverter.class)
    private List<Album> albumesFavoritos;


    @TypeConverters(DataConverter.class)
    private List<Playlist> playlistsFavoritos;


    @Ignore
    private List<Artist> artistasFavoritos;


    public DatosUsuario() {

    }



    @Ignore
    public DatosUsuario(List<Track> tracksFavoritos, List<Album> albumesFavoritos, List<Artist> artistasFavoritos, List<Playlist> playlistsFavoritos) {
        this.tracksFavoritos = tracksFavoritos;
        this.albumesFavoritos = albumesFavoritos;
        this.artistasFavoritos = artistasFavoritos;
        this.playlistsFavoritos = playlistsFavoritos;
    }

    public int getIdROOM() {
        return idROOM;
    }

    public void setIdROOM(int idROOM) {
        this.idROOM = idROOM;
    }

    @TypeConverters(DataConverter.class)
    public List<Track> getTracksFavoritos() {
        return tracksFavoritos;
    }

    @TypeConverters(DataConverter.class)
    public List<Album> getAlbumesFavoritos() {
        return albumesFavoritos;
    }

    @TypeConverters(DataConverter.class)
    public List<Playlist> getPlaylistsFavoritos() {
        return playlistsFavoritos;
    }

    public void setPlaylistsFavoritos(List<Playlist> playlistsFavoritos) {
        this.playlistsFavoritos = playlistsFavoritos;
    }

    public List<Artist> getArtistasFavoritos() {
        return artistasFavoritos;
    }

    public void setArtistasFavoritos(List<Artist> artistasFavoritos) {
        this.artistasFavoritos = artistasFavoritos;
    }

    public void setTracksFavoritos(List<Track> tracksFavoritos) {
        this.tracksFavoritos = tracksFavoritos;
    }

    public void setAlbumesFavoritos(List<Album> albumesFavoritos) {
        this.albumesFavoritos = albumesFavoritos;
    }
}
