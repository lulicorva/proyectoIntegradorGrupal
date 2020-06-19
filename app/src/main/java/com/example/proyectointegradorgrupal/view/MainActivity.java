package com.example.proyectointegradorgrupal.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.controller.AlbumController;
import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.model.Playlist;
import com.example.proyectointegradorgrupal.model.Recomendados;
import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.util.ResultListener;
import com.example.proyectointegradorgrupal.view.fragment.BottomNavigationFragment;
import com.example.proyectointegradorgrupal.view.fragment.FragmentListadoCanciones;
import com.example.proyectointegradorgrupal.view.fragment.FragmentPrincipalPrueba;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements FragmentPrincipalPrueba.FragmentPrincipalListener {

    private RelativeLayout relativeLayout;
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;

    @Override
//TODO me parece que el FavoritosFragment deveria ser un FragmentPantallaInicio y dentro de ese poner 3 recyclerview, uno de favoritos, otro de
//tus playlists y otro de recomendados, todos dentro del mismo fragment
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       //Esta parte iria en el fragment, aca está para probar el pedido
        AlbumController albumController = new AlbumController();
        albumController.getAlbumTracks("12047960", new ResultListener<Track>() {
            @Override
            public void onFinish(Track result) {

            }
        });


        pegarFragmentsMainActivity();

    }
/**
 * Esto es para que al apretar atras no se cierre la app
 * */
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();

        } else {
            super.onBackPressed();

        }

    }
    /**
     * Método para los FindViewsByIds
     */
    private void findViewsById() {
        bottomNavigationView = findViewById(R.id.fragmentBottomNavigation);
        relativeLayout = findViewById(R.id.activityMainRelativeLayout);
        toolbar = findViewById(R.id.activityMainToolbar);
    }

    /**
     * Metodo para pegar fragments
     */
    private void pegarFragmentsMainActivity() {

        pegarFragment(new FragmentPrincipalPrueba(), R.id.activityMainContenedorPrincipalPrueba);
       // pegarFragment(new FavoritosFragment(), R.id.activityMainContenedorFragmenFavoritos);
       // pegarFragment(new PlaylistFragment(), R.id.activityMainContenedorFragmenPlaylist);
        //pegarFragment(new RecomendadosFragment(), R.id.activityMainContenedorFragmenRecomendados);
        pegarFragment(new BottomNavigationFragment(), R.id.activityMainContenedorFragmentBottomNavigation);

    }

    private void pegarFragment(Fragment favoritosFragment, int id) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(id, favoritosFragment);
        fragmentTransaction.commit();
    }



    @Override
    public void onClick(Album album) {
       // Intent intent = new Intent(this, ListadoCancionesActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("favorito", album);


         FragmentListadoCanciones fragmentListadoCanciones = new FragmentListadoCanciones();
         fragmentListadoCanciones.setArguments(bundle);
         pegarFragment(fragmentListadoCanciones, R.id.activityMainContenedorPrincipalPrueba);
         //Estas lineas se reemplazan por el intent (primera linea y ultimas 2 del metodo) pasas directamente al fragmentListadoFavoritos sin pasar por ListadoCancionesActivity

  /*      intent.putExtras(bundle);
        startActivity(intent);
  */  }

    @Override
    public void onClick(Playlist playlist) {

    }

    @Override
    public void onClick(Recomendados recomendados) {

    }

    /**
     * Configuracion del Appbar superior, Buscador y Configuracion - CHEQUEAR color
     * @param menu
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_general,menu);

        final MenuItem configuracionItem = menu.findItem(R.id.menuGeneralConfiguracion);
        final MenuItem buscarItem = menu.findItem(R.id.menuGeneralBuscar);

        final SearchView searchView = (SearchView) buscarItem.getActionView();

        //Boton configuracion
        configuracionItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this, "Boton Configuracion", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, "Boton Buscar", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(MainActivity.this, newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
