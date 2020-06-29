package com.example.proyectointegradorgrupal.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.proyectointegradorgrupal.LoginActivity;
import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.ReproductorActivity;
import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.model.Playlist;
import com.example.proyectointegradorgrupal.model.Recomendados;
import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.view.fragment.BottomNavigationFragment;
import com.example.proyectointegradorgrupal.view.fragment.FragmentAlbumTracks;
import com.example.proyectointegradorgrupal.view.fragment.FragmentPlaylistTracks;
import com.example.proyectointegradorgrupal.view.fragment.FragmentPrincipal;
import com.example.proyectointegradorgrupal.view.fragment.FragmentReproductorSingleton;
import com.example.proyectointegradorgrupal.view.fragment.FragmentSearch;
import com.example.proyectointegradorgrupal.view.fragment.biblioteca.FragmentAlbumsFavoritos;
import com.example.proyectointegradorgrupal.view.fragment.biblioteca.FragmentPLaylistsFavoritos;
import com.example.proyectointegradorgrupal.view.fragment.biblioteca.FragmentTracksFavoritos;
import com.example.proyectointegradorgrupal.view.fragment.biblioteca.FragmentTuBiblioteca;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;


public class MainActivity extends AppCompatActivity implements FragmentPrincipal.FragmentPrincipalListener, FragmentSearch.FragmentSearchListener, FragmentAlbumTracks.FragmentListaCancionesListener, FragmentPlaylistTracks.FragmentPlaylistTracksListener, BottomNavigationFragment.FragmentBottomNavigationListener, FragmentTracksFavoritos.FragmentTracksFavoritosListener, FragmentAlbumsFavoritos.FragmentAlbumsFavoritosListener, FragmentPLaylistsFavoritos.FragmentPlaylistsFavoritosListener {

    public static final String PLAYLIST = "playlist";
    private DrawerLayout drawerLayout;
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    public static final String QUERY = "query";
    public static final String FAVORITO = "favorito";
    private FragmentTuBiblioteca fragmentTuBiblioteca;

    @Override
//TODO me parece que el FavoritosFragment deveria ser un FragmentPantallaInicio y dentro de ese poner 3 recyclerview, uno de favoritos, otro de
//tus playlists y otro de recomendados, todos dentro del mismo fragment
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        //Para autenticacion de Google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        fragmentTuBiblioteca = new FragmentTuBiblioteca();

        findViewsById();
        configuracionToolbar();
        pegarFragmentsMainActivity();

    }


    private void configuracionToolbar() {
        toolbar.setTitle("Jaxoo");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawers, R.string.close_drawers);
        //drawerLayout.addDrawerListener(actionBarDrawerToggle);
        // actionBarDrawerToggle.syncState();
    }

    /**
     * Esto es para que al apretar atras no se cierre la app
     */
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
        drawerLayout = findViewById(R.id.activityMainDrawerLayout);
        toolbar = findViewById(R.id.activityMainToolbar);
    }


    private void pegarFragment(Fragment fragment, int id) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(id, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * Metodos que se usan varias veces
     */
    private void abrirAlbumTracks(Album album) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(MainActivity.FAVORITO, album);


        FragmentAlbumTracks fragmentAlbumTracks = new FragmentAlbumTracks();
        fragmentAlbumTracks.setArguments(bundle);
        pegarFragment(fragmentAlbumTracks, R.id.activityMainContenedorPrincipal);
    }


    private void abrirPlaylistTracks(Playlist playlist) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(PLAYLIST, playlist);
        FragmentPlaylistTracks fragmentPlaylistTracks = new FragmentPlaylistTracks();
        fragmentPlaylistTracks.setArguments(bundle);
        pegarFragment(fragmentPlaylistTracks, R.id.activityMainContenedorPrincipal);
    }

    /**
     * Metodo para pegar fragments
     */
    private void pegarFragmentsMainActivity() {

        pegarFragment(new FragmentPrincipal(), R.id.activityMainContenedorPrincipal);

        //Este siempre esta, va cambiando el de arriba
        pegarFragment(new BottomNavigationFragment(), R.id.activityMainContenedorFragmentBottomNavigation);

    }


    /**
     * Metodos onCLick de los fragments
     */
    @Override
    public void onClickAlbum(Album album) {

        abrirAlbumTracks(album);


    }

    @Override
    public void onClickPlaylist(Playlist playlist) {
        abrirPlaylistTracks(playlist);


    }

    /**
     * onCLick FragmentPrincipal
     */
    @Override
    public void onClickRecomendado(Recomendados recomendados) {
        //abrir fragment con recomendados
    }



    @Override
    public void onClickTrackDesdeSearch(List<Track> trackList, int position) {
        abrirReproductorActivity(trackList, position);
    }

    @Override
    public void onClickTrackDesdePlaylist(List<Track> trackList, int position) {
        abrirReproductorActivity(trackList, position);
    }


    @Override
    public void onClickTrackDesdeAlbum(List<Track> trackList, int position) {

        abrirReproductorActivity(trackList, position);


    }




    /**
     * Configuracion del Appbar superior, Buscador y Configuracion - CHEQUEAR color
     *
     * @param menu
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_general, menu);

        final MenuItem cerrarItem = menu.findItem(R.id.menuGeneralCerrar);
        final MenuItem buscarItem = menu.findItem(R.id.menuGeneralBuscar);

        final SearchView searchView = (SearchView) buscarItem.getActionView();

        //Boton cerrar sesion
        cerrarItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuGeneralCerrar:
                        logOutFirebaseUser();
                        logOutGoogle();

                        Intent i = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(i);
                        break;
                }

                return false;
            }
        });


        //Boton Busqueda
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, "Boton Buscar", Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putString(MainActivity.QUERY, query);
                FragmentSearch fragmentSearch = new FragmentSearch();
                fragmentSearch.setArguments(bundle);
                pegarFragment(fragmentSearch, R.id.activityMainContenedorPrincipal);

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


    private void logOutFirebaseUser() {
        mAuth.signOut();
        Toast.makeText(this, "Cerrar sesión", Toast.LENGTH_SHORT).show();
    }

    private void logOutGoogle() {
        mGoogleSignInClient.signOut().addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                Toast.makeText(MainActivity.this, "Gracias por quedarte", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this, "Volvé pronto", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * onCLick BottomNavigationFragmenet
     */
    @Override
    public void onClickTuBiblioteca() {
        pegarFragment(fragmentTuBiblioteca, R.id.activityMainContenedorPrincipal);


    }

    @Override
    public void onCLickPerfil() {
        FragmentReproductorSingleton fragmentReproductorSingleton = new FragmentReproductorSingleton();
        pegarFragment(fragmentReproductorSingleton, R.id.activityMainContenedorPrincipal);

        //pegar fragment Mi perfil
    }

    /**
     * Interfaces de fragments dentro de TuBiblioteca
     */


    @Override
    public void onClickTrackFavoritos(List<Track> trackList, int position) {
        abrirReproductorActivity(trackList, position);
    }

    @Override
    public void onClickAlbumFavorito(Album album) {
        abrirAlbumTracks(album);
    }


    @Override
    public void onClickPlaylistFavorito(Playlist playlist) {
        abrirPlaylistTracks(playlist);
    }




    private void abrirReproductorActivity(List<Track> trackList, int position) {
        Intent intent = new Intent(MainActivity.this, ReproductorActivity.class);
        Bundle bundle = new Bundle();
        Track track = new Track();
        track.setData(trackList);
        bundle.putInt("position", position);
        bundle.putSerializable("trackList", track);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

