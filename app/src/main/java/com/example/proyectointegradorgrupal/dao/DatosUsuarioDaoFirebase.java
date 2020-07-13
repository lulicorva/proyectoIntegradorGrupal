package com.example.proyectointegradorgrupal.dao;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.proyectointegradorgrupal.LoginActivity;
import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.model.DatosUsuario;
import com.example.proyectointegradorgrupal.model.Playlist;
import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.util.ResultListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class DatosUsuarioDaoFirebase {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;


    private CollectionReference reference;


    public DatosUsuarioDaoFirebase() {

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        reference = db.collection(LoginActivity.DATOS_USUARIO);

    }

    public void getDatosUsuario(final ResultListener<DatosUsuario> resultListener) {
        reference.document(mAuth.getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                DatosUsuario datosUsuario = documentSnapshot.toObject(DatosUsuario.class);
                resultListener.onFinish(datosUsuario);
            }
        });

    }


    public void setDatosUsuario(final DatosUsuario datosUsuario, final ResultListener<DatosUsuario> resultListenerFromController) {
               reference.document(firebaseUser.getUid())
                .set(datosUsuario)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        resultListenerFromController.onFinish(datosUsuario);

                    }
                });

    }


    public void setAlbumFavorito(final Album album, final ResultListener<Album> resultListenerFromController){
        reference.document(firebaseUser.getUid())
                .update("albumesFavoritos", FieldValue.arrayUnion(album))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                      resultListenerFromController.onFinish(album);


                    }
                });

    }

    public void setTrackFavorito(final Track trackFavorito, final ResultListener<Track> resultListenerFromController){
        reference.document(firebaseUser.getUid())
                .update("tracksFavoritos", FieldValue.arrayUnion(trackFavorito))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                      resultListenerFromController.onFinish(trackFavorito);


                    }
                });


    }

    public void setPlaylistFavorita(final Playlist playlistFavorita, final ResultListener<Playlist> resultListenerFromController){
        reference.document(firebaseUser.getUid())
                .update("playlistsFavoritos", FieldValue.arrayUnion(playlistFavorita))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                      resultListenerFromController.onFinish(playlistFavorita);

                    }
                });
    }





}
