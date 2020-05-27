package com.example.proyectointegradorgrupal.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.controller.AlbumController;
import com.example.proyectointegradorgrupal.controller.PlaylistController;
import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.model.ConteinerPlayList;
import com.example.proyectointegradorgrupal.model.Favoritos;
import com.example.proyectointegradorgrupal.model.Playlist;
import com.example.proyectointegradorgrupal.util.ResultListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


public class MainActivity extends AppCompatActivity implements FavoritosFragment.RecyclerViewFragmentFavoritosListener {

    private BottomNavigationView bottomNavigationView;

    @Override
//TODO me parece que el FavoritosFragment deveria ser un FragmentPantallaInicio y dentro de ese poner 3 recyclerview, uno de favoritos, otro de
//tus playlists y otro de recomendados, todos dentro del mismo fragment
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlbumController albumController = new AlbumController();
        albumController.getAlbum(new ResultListener<Album>() {
            @Override
            public void onFinish(Album result) {
                //aca hace algo cuando llega el resultado
            }
        });




        bottomNavigationView = findViewById(R.id.activityMainBottomNavigation);


        FavoritosFragment favoritosFragment = new FavoritosFragment();
        pegarFragment(favoritosFragment);
        PlaylistFragment playlistFragment = new PlaylistFragment();
        pegarFragmentPlaylist(playlistFragment);
        RecomendadosFragment recomendadosFragment = new RecomendadosFragment();
        pegarFragmentRecomendados(recomendadosFragment);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menuInicio:
                        Toast.makeText(MainActivity.this, "Inicio", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuBuscar:
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(intent);
                        break;
                    case R.id.menuTuBiblioteca:
                        Toast.makeText(MainActivity.this, "Tu Biblioteca", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });


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

        /**
        FragmentListadoCanciones fragmentListadoCanciones = new FragmentListadoCanciones();
        fragmentListadoCanciones.setArguments(bundle);
        pegarFragment(fragmentListadoCanciones);
        Estas lineas se reemplazan por las 75, 82 y 83 y pasas directamente al fragmentListadoFavoritos sin pasar por ListadoCancionesActivity
         */

        intent.putExtras(bundle);
        startActivity(intent);
    }


    private void pegarFragmentPlaylist(PlaylistFragment playlistFragment) {
        FragmentManager supportFragmentManagerPlaylist = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionPlaylist = supportFragmentManagerPlaylist.beginTransaction();
        fragmentTransactionPlaylist.replace(R.id.activityMainContenedorFragmenPlaylist, playlistFragment);
        fragmentTransactionPlaylist.commit();
    }

    //Solucionar

    private void pegarFragmentRecomendados(RecomendadosFragment recomendadosFragment) {
        FragmentManager supprtFragmentManagerRecomendados = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionRecomendados = supprtFragmentManagerRecomendados.beginTransaction();
        fragmentTransactionRecomendados.replace(R.id.activityMainContenedorFragmenRecomendados, recomendadosFragment);
        fragmentTransactionRecomendados.commit();
    }

    //Solucionar
}