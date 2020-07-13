package com.example.proyectointegradorgrupal.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.proyectointegradorgrupal.model.DatosUsuario;


import java.util.List;

@Dao
public interface DatosUsuarioDaoRoom {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(DatosUsuario... datosUsuario);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<DatosUsuario> datosUsuarios);


    @Delete
    void delete(DatosUsuario datosUsuario);

    @Query("DELETE from DatosUsuario")
    void deleteALl();


    @Query("SELECT * FROM DatosUsuario")
    DatosUsuario getDatosUsuario();

}
