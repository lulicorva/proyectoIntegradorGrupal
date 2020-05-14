package com.example.proyectointegradorgrupal.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.model.Favoritos;


//TODO me parece que el FavoritosFragment deveria ser un FragmentPantallaInicio y dentro de ese poner 3 recyclerview, uno de favoritos, otro de
//tus playlists y otro de recomendados, todos dentro del mismo fragment


public class MainActivity extends AppCompatActivity implements FavoritosFragment.RecyclerViewFragmentFavoritosListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FavoritosFragment favoritosFragment = new FavoritosFragment();

        pegarFragment(favoritosFragment);


    }

    private void pegarFragment(FavoritosFragment favoritosFragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activityMainContenedorFragmenFavoritos, favoritosFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(Favoritos favoritos) {
        Intent intent = new Intent(this, ListadoCancionesActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("favorito", favoritos);
        intent.putExtras(bundle);


        startActivity(intent);
    }
}
