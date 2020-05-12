package com.example.proyectointegradorgrupal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FavoritosFragment favoritosFragment= new FavoritosFragment();

        pegarFragment(favoritosFragment);

        playlist_fragment playlistFragment = new playlist_fragment();

        pegarFragmentPlaylist(playlistFragment);

        RecomendadosFragment recomendadosFragment = new RecomendadosFragment();
        pegarFragmentRecomendados(recomendadosFragment);
    }





    private void pegarFragment(FavoritosFragment favoritosFragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activityMainContenedorFragmenFavoritos,favoritosFragment);
        fragmentTransaction.commit();



    }

   private void pegarFragmentPlaylist(playlist_fragment playlistFragment){
        FragmentManager supportFragmentManagerPlaylist = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionPlaylist = supportFragmentManagerPlaylist.beginTransaction();
        fragmentTransactionPlaylist.replace(R.id.activityMainContenedorFragmenPlaylist,playlistFragment);
        fragmentTransactionPlaylist.commit();

   }

   private void pegarFragmentRecomendados(RecomendadosFragment recomendadosFragment) {
       FragmentManager supprtFragmentManagerRecomendados = getSupportFragmentManager();
       FragmentTransaction fragmentTransactionRecomendados = supprtFragmentManagerRecomendados.beginTransaction();
       fragmentTransactionRecomendados.replace(R.id.activityMainContenedorFragmenRecomendados,recomendadosFragment);
       fragmentTransactionRecomendados.commit();
   }



}
