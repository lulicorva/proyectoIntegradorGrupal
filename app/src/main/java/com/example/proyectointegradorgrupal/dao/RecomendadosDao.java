package com.example.proyectointegradorgrupal.dao;

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.model.Recomendados;

import java.util.ArrayList;
import java.util.List;

public class RecomendadosDao {

    public static List<Recomendados> getRecomendados(){
        List<Recomendados> recomendadosList = new ArrayList<>();

        recomendadosList.add(new Recomendados("Snarky Puppy", R.drawable.sanarky_puppy));
        recomendadosList.add(new Recomendados("Spinetta",R.drawable.spinetta));
        recomendadosList.add(new Recomendados("Frank Zappa",R.drawable.frank_zappa));
        recomendadosList.add(new Recomendados("Led Zepellin",R.drawable.led_zepellin));

        return recomendadosList;
    }



}
