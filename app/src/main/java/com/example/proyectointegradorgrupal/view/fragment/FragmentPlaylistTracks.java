package com.example.proyectointegradorgrupal.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.proyectointegradorgrupal.LoginActivity;
import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.controller.PlaylistController;
import com.example.proyectointegradorgrupal.model.Playlist;
import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.util.ResultListener;
import com.example.proyectointegradorgrupal.view.MainActivity;
import com.example.proyectointegradorgrupal.view.adapter.TrackAdapter;
import com.example.proyectointegradorgrupal.view.adapter.ViewPagerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;


public class FragmentPlaylistTracks extends Fragment implements TrackAdapter.TrackAdapterListener {

    private RecyclerView recyclerView;
    private PlaylistController playlistController;
    private FragmentPlaylistTracksListener fragmentPlaylistTracksListener;
    private TrackAdapter trackAdapter;
    private ImageButton botonPlaylistFavorito;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private Playlist playlist;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.fragmentPlaylistTracksListener = (FragmentPlaylistTracksListener) context;

    }

    public FragmentPlaylistTracks() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist_tracks, container, false);

        recyclerView = view.findViewById(R.id.fragmentPlaylistRecycler);
        botonPlaylistFavorito = view.findViewById(R.id.playlistBotonFavoritos);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        Bundle bundle = getArguments();

        playlist = (Playlist) bundle.getSerializable(MainActivity.PLAYLIST);

        ImageView fragmentPlaylistImagen = view.findViewById(R.id.fragmentPlaylistImagen);
        TextView fragmentPlaylistNombre = view.findViewById(R.id.fragmentPlaylistNombre);

        fragmentPlaylistNombre.setText(playlist.getTitle());


        playlistController = new PlaylistController();
        playlistController.getPlaylistTracks(playlist.getId(), new ResultListener<Track>() {
            @Override
            public void onFinish(Track result) {
                trackAdapter = new TrackAdapter(result.getData(), FragmentPlaylistTracks.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(trackAdapter);

            }
        });
        Glide.with(fragmentPlaylistImagen.getContext())
                .load(playlist.getPictureXL())
                .into(fragmentPlaylistImagen);


        botonPlaylistFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAuth.getCurrentUser() != null) {
                    db.collection(LoginActivity.DATOS_USUARIO)
                            .document(currentUser.getUid())
                            .update("playlistsFavoritos", FieldValue.arrayUnion(playlist))
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getActivity(), "Playlist agregada a favoritos", Toast.LENGTH_SHORT).show();
                                    botonPlaylistFavorito.setImageResource(R.drawable.ic_favorite);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Salio mal agregar playlist", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(getActivity(), "Accede a tu cuenta para agregar favoritos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onClick(Track track) {
        fragmentPlaylistTracksListener.onClickTrackDesdePlaylist(track);
    }

    public interface FragmentPlaylistTracksListener {
        public void onClickTrackDesdePlaylist(Track track);
    }
}