package com.example.proyectointegradorgrupal.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.model.Favoritos;

public class MainActivity extends AppCompatActivity implements FavoritosFragment.RecyclerViewFragmentFavoritosListener {
    @Override
//TODO me parece que el FavoritosFragment deveria ser un FragmentPantallaInicio y dentro de ese poner 3 recyclerview, uno de favoritos, otro de
//tus playlists y otro de recomendados, todos dentro del mismo fragment
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FavoritosFragment favoritosFragment = new FavoritosFragment();
        pegarFragment(favoritosFragment);
        playlist_fragment playlistFragment = new playlist_fragment();
        pegarFragmentPlaylist(playlistFragment);
        RecomendadosFragment recomendadosFragment = new RecomendadosFragment();
        pegarFragmentRecomendados(recomendadosFragment);
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



    private void pegarFragmentPlaylist(playlist_fragment playlistFragment) {
        FragmentManager supportFragmentManagerPlaylist = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionPlaylist = supportFragmentManagerPlaylist.beginTransaction();
        fragmentTransactionPlaylist.replace(R.id.activityMainContenedorFragmenPlaylist, playlistFragment);
        fragmentTransactionPlaylist.commit();
    }

    private void pegarFragmentRecomendados(RecomendadosFragment recomendadosFragment) {
        FragmentManager supprtFragmentManagerRecomendados = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionRecomendados = supprtFragmentManagerRecomendados.beginTransaction();
        fragmentTransactionRecomendados.replace(R.id.activityMainContenedorFragmenRecomendados, recomendadosFragment);
        fragmentTransactionRecomendados.commit();
    }
}