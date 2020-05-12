package com.example.proyectointegradorgrupal;

import java.util.ArrayList;
import java.util.List;

public class ProveedorDeRecomendados {

    public static List<Recomendados> getRecomendados(){
        List<Recomendados> recomendadosList = new ArrayList<>();

        recomendadosList.add(new Recomendados("Snarky Puppy",R.drawable.sanarky_puppy));
        recomendadosList.add(new Recomendados("Spinetta",R.drawable.spinetta));
        recomendadosList.add(new Recomendados("Frank Zappa",R.drawable.frank_zappa));
        recomendadosList.add(new Recomendados("Led Zepellin",R.drawable.led_zepellin));

        return recomendadosList;
    }



}
