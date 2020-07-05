package com.example.proyectointegradorgrupal.configuration;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.proyectointegradorgrupal.dao.room.DatosUsuarioDaoRoom;
import com.example.proyectointegradorgrupal.model.Track;

@Database(entities = {Track.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DatosUsuarioDaoRoom trackDaoRoom();


    private static AppDatabase INSTANCE = null;

    public AppDatabase getInstance(Context context) {

        if (INSTANCE == null) {

            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "track-db")
                    .build();
        }
        return INSTANCE;
    }
}


