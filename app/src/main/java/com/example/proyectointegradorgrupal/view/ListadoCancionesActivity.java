package com.example.proyectointegradorgrupal.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.view.fragment.FragmentListadoCanciones;

public class ListadoCancionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_canciones);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        FragmentListadoCanciones fragmentListadoCanciones = new FragmentListadoCanciones();

        fragmentListadoCanciones.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activityListadoCancionesContenedorFragment, fragmentListadoCanciones)
                .commit();

    }
}
