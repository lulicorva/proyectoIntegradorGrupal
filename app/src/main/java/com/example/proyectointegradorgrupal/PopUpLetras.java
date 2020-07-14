package com.example.proyectointegradorgrupal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectointegradorgrupal.controller.LyricsController;
import com.example.proyectointegradorgrupal.model.Example;
import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.util.ResultListener;

public class PopUpLetras extends AppCompatActivity {

    private TextView textViewLetra;
    private TextView textViewNombreArtistaCancion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_letras);


        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;

        getWindow().setLayout((int) (ancho * 0.75),(int) (alto * 0.75));

        textViewLetra = findViewById(R.id.texViewPopUp);
        textViewNombreArtistaCancion = findViewById(R.id.textViewPopUpNombreArtistaCancion);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        final Track track = (Track) bundle.get("track");


        textViewNombreArtistaCancion.setText(track.getArtist().getName() + " - " + track.getTitle());

        LyricsController lyricsController = new LyricsController();
        lyricsController.getLyrics(track.getTitle(), track.getArtist().getName(), new ResultListener<Example>() {
            @Override
            public void onFinish(Example result) {
                Example example = result;
                textViewLetra.setText(example.getMessage().getBody().getLyrics().getLyricsBody());


            }
            });
        }
}