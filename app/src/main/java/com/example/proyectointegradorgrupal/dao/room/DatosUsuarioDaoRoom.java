package com.example.proyectointegradorgrupal.dao.room;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proyectointegradorgrupal.model.DatosUsuario;
import com.example.proyectointegradorgrupal.model.Track;

import java.util.List;

@Dao
public interface DatosUsuarioDaoRoom {

    @Insert
    void insertAll(DatosUsuario... datosUsuario);

    @Insert
    void insertAll(List<DatosUsuario> datosUsuarios);


    @Delete
    void delete(DatosUsuario datosUsuario);


    @Query("SELECT * FROM DatosUsuario")
    List<DatosUsuario> getDatosUsuario();

}
