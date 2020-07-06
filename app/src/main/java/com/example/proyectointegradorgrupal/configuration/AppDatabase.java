package com.example.proyectointegradorgrupal.configuration;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.proyectointegradorgrupal.dao.DatosUsuarioDaoRoom;
import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.model.DatosUsuario;
import com.example.proyectointegradorgrupal.model.Playlist;
import com.example.proyectointegradorgrupal.model.Track;



@Database(entities = {DatosUsuario.class, Track.class, Album.class, Playlist.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DatosUsuarioDaoRoom datosUsuarioDaoRoom();


    private static AppDatabase INSTANCE = null;

    public static AppDatabase getInstance(Context context) {

        if (INSTANCE == null) {

            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "datosUsuario-db")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
}


