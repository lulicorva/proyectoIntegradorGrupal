package com.example.proyectointegradorgrupal.view.fragment.biblioteca;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proyectointegradorgrupal.LoginActivity;
import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.model.DatosUsuario;
import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.view.adapter.TrackAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;


public class FragmentTracksFavoritos extends Fragment {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private TrackAdapter trackAdapter;
    private RecyclerView recyclerView;
    private List<Track> trackListFavoritos;
    private FragmentTracksFavoritosListener fragmentTracksFavoritosListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.fragmentTracksFavoritosListener = (FragmentTracksFavoritosListener) context;
    }

    public FragmentTracksFavoritos() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tracks_favoritos, container, false);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        recyclerView = view.findViewById(R.id.recyclerTracksFavoritos);

        db.collection(LoginActivity.DATOS_USUARIO)
                .document(mAuth.getCurrentUser().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                DatosUsuario datosUsuario = documentSnapshot.toObject(DatosUsuario.class);
                if (datosUsuario.getTracksFavoritos() != null) {

                    trackListFavoritos = datosUsuario.getTracksFavoritos();
                    trackAdapter = new TrackAdapter(trackListFavoritos, new TrackAdapter.TrackAdapterListener() {
                        @Override
                        public void onClick(List<Track> trackList) {

                        }

                        @Override
                        public void onClick(Track track) {
                            fragmentTracksFavoritosListener.onClickTrackFavorito(track);
                        }

                    });

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(trackAdapter);


                } else {

                    Toast.makeText(getActivity(), "Aun no tienes favoritos", Toast.LENGTH_SHORT).show();

                }


            }
        }).addOnFailureListener(new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getActivity(), "Error al importar lista", Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }

    public interface FragmentTracksFavoritosListener {
        public void onClickTrackFavorito(Track track);
    }
}