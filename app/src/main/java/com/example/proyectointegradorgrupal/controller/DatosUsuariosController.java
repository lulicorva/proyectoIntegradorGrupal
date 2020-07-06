package com.example.proyectointegradorgrupal.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.proyectointegradorgrupal.configuration.AppDatabase;
import com.example.proyectointegradorgrupal.dao.DatosUsuarioDaoFirebase;
import com.example.proyectointegradorgrupal.dao.DatosUsuarioDaoRoom;
import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.model.DatosUsuario;
import com.example.proyectointegradorgrupal.model.Playlist;
import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.util.ResultListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class DatosUsuariosController {

    private DatosUsuarioDaoFirebase datosUsuarioDaoFirebase;
    private DatosUsuarioDaoRoom datosUsuarioDaoRoom;
    private Context context;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;

    public DatosUsuariosController(Context context) {
        this.context = context;
        this.datosUsuarioDaoFirebase = new DatosUsuarioDaoFirebase();
        this.datosUsuarioDaoRoom = AppDatabase.getInstance(context).datosUsuarioDaoRoom();


        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();


    }

    public void getDatosUsuario(final ResultListener<DatosUsuario> resultListener) {


        if (hayInternet()) {

            datosUsuarioDaoFirebase.getDatosUsuario(new ResultListener<DatosUsuario>() {
                @Override
                public void onFinish(DatosUsuario result) {
                    datosUsuarioDaoRoom.deleteALl();
                    datosUsuarioDaoRoom.insertAll(result);
                    resultListener.onFinish(result);
                }
            });
        } else {
            DatosUsuario datosUsuario = datosUsuarioDaoRoom.getDatosUsuario();
            resultListener.onFinish(datosUsuario);


            Toast.makeText(context, "No hay conexion", Toast.LENGTH_SHORT).show();

        }

    }

    public void setDatosUsuario(final ResultListener<DatosUsuario> resultListenerFromView) {
        datosUsuarioDaoFirebase.setDatosUsuario(new DatosUsuario(), new ResultListener<DatosUsuario>() {
            @Override
            public void onFinish(DatosUsuario result) {

                datosUsuarioDaoRoom.deleteALl();
                datosUsuarioDaoRoom.insertAll(result);
                resultListenerFromView.onFinish(result);

            }
        });


    }

    public void setAlbumFavorito(Album albumFavorito, final ResultListener<Album> resultListenerFromView) {
        datosUsuarioDaoFirebase.setAlbumFavorito(albumFavorito, new ResultListener<Album>() {
            @Override
            public void onFinish(Album result) {
                resultListenerFromView.onFinish(result);

            }
        });
    }

    public void setTrackFavorito(Track trackFavorito, final ResultListener<Track> resultListenerFromView) {
        datosUsuarioDaoFirebase.setTrackFavorito(trackFavorito, new ResultListener<Track>() {
            @Override
            public void onFinish(Track result) {
                resultListenerFromView.onFinish(result);
            }
        });
    }

    public void setPlaylistFavorita(Playlist playlistFavorita, final ResultListener<Playlist> resultListenerFromView) {
        datosUsuarioDaoFirebase.setPlaylistFavorita(playlistFavorita, new ResultListener<Playlist>() {
            @Override
            public void onFinish(Playlist result) {
                resultListenerFromView.onFinish(result);
            }
        });
    }


    public boolean hayInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();

        return actNetInfo != null && actNetInfo.isConnected();


    }


}

