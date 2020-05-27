package com.example.proyectointegradorgrupal.dao;

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.model.Favoritos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public abstract class FavoritosDao  {

    public static List<Favoritos> getFavoritos() {
        List<Favoritos> favoritosList = new ArrayList<>();




        favoritosList.add(new Favoritos("Album", R.drawable.vulfpeck));
        favoritosList.add(new Favoritos("Barbi Recanti", R.drawable.barbi_recanati));
        favoritosList.add(new Favoritos("Jorja Smith",R.drawable.jorga_smith));
        favoritosList.add(new Favoritos("Red Hot Chili Peppers",R.drawable.red_hot));


        return favoritosList;


    }

}
