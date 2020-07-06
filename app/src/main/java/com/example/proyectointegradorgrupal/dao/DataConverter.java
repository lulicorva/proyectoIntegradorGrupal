package com.example.proyectointegradorgrupal.dao;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.model.Playlist;
import com.example.proyectointegradorgrupal.model.Track;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;


public class DataConverter {

    @TypeConverter
    public String tracksFavoritosToJson(List<Track> tracksFavoritos) {
        if (tracksFavoritos == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Track>>() {}.getType();
        String json = gson.toJson(tracksFavoritos, type);
        return json;
    }

    @TypeConverter
    public List<Track> tracksFavoritosStringToList(String tracksFavoritosString) {
        if (tracksFavoritosString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Track>>() {}.getType();
        List<Track> tracksFavoritos = gson.fromJson(tracksFavoritosString, type);
        return tracksFavoritos;
    }


    @TypeConverter
    public String albumsFavoritosToJson(List<Album> albumsFavoritos) {
        if (albumsFavoritos == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Album>>() {}.getType();
        String json = gson.toJson(albumsFavoritos, type);
        return json;
    }

    @TypeConverter
    public List<Album> albumsFavoritosStringToList(String albumFavoritosString) {
        if (albumFavoritosString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Album>>() {}.getType();
        List<Album> albumsFavoritos = gson.fromJson(albumFavoritosString, type);
        return albumsFavoritos;
    }

    @TypeConverter
    public String playlistFavoritasStringToList(List<Playlist> playlistFavoritas) {
        if (playlistFavoritas == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Playlist>>() {}.getType();
        String json = gson.toJson(playlistFavoritas, type);
        return json;
    }

    @TypeConverter
    public List<Playlist> playlistFavoritosStringToList(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Playlist>>() {}.getType();
        List<Playlist> playlistFavoritos = gson.fromJson(countryLangString, type);
        return playlistFavoritos;
    }
}
