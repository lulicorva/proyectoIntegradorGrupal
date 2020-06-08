package com.example.proyectointegradorgrupal.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.controller.AlbumController;
import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.util.ResultListener;
import com.example.proyectointegradorgrupal.view.fragment.BottomNavigationFragment;
import com.example.proyectointegradorgrupal.view.fragment.FavoritosFragment;
import com.example.proyectointegradorgrupal.view.fragment.PlaylistFragment;
import com.example.proyectointegradorgrupal.view.fragment.RecomendadosFragment;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements FavoritosFragment.RecyclerViewFragmentFavoritosListener {


    @Override
//TODO me parece que el FavoritosFragment deveria ser un FragmentPantallaInicio y dentro de ese poner 3 recyclerview, uno de favoritos, otro de
//tus playlists y otro de recomendados, todos dentro del mismo fragment
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AlbumController albumController = new AlbumController();
        albumController.getAlbumTracks("12047960", new ResultListener<Track>() {
            @Override
            public void onFinish(Track result) {

            }
        });



        pegarFragmentsMainActivity();


    }

    private void pegarFragmentsMainActivity() {

        pegarFragment(new FavoritosFragment(), R.id.activityMainContenedorFragmenFavoritos);
        pegarFragment(new PlaylistFragment(), R.id.activityMainContenedorFragmenPlaylist);
        pegarFragment(new RecomendadosFragment(), R.id.activityMainContenedorFragmenRecomendados);
        pegarFragment(new BottomNavigationFragment(),R.id.activityMainContenedorFragmentBottomNavigation);
    }

    private void pegarFragment(Fragment favoritosFragment, int id) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(id, favoritosFragment);
        fragmentTransaction.commit();
    }



    @Override
    public void onClick(Album album) {
        Intent intent = new Intent(this, ListadoCancionesActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("favorito", album);

        /**
         FragmentListadoCanciones fragmentListadoCanciones = new FragmentListadoCanciones();
         fragmentListadoCanciones.setArguments(bundle);
         pegarFragment(fragmentListadoCanciones);
         Estas lineas se reemplazan por el intent (primera linea y ultimas 2 del metodo) pasas directamente al fragmentListadoFavoritos sin pasar por ListadoCancionesActivity
         */

        intent.putExtras(bundle);
        startActivity(intent);
    }

    //Solucionar
}