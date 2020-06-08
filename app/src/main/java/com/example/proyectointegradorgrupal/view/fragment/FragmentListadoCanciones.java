package com.example.proyectointegradorgrupal.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.controller.AlbumController;
import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.model.Favoritos;
import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.util.ResultListener;
import com.example.proyectointegradorgrupal.view.adapter.TrackAdapter;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListadoCanciones extends Fragment implements TrackAdapter.TrackAdapterListener {

    private RecyclerView recyclerViewListaCanciones;
    private RecyclerViewListaCancionesListener recyclerViewListaCancionesListener;
    private AlbumController albumController;

    public FragmentListadoCanciones() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listado_canciones, container, false);

        recyclerViewListaCanciones = view.findViewById(R.id.fragmentListadoCancionesRecycler);

        Bundle bundle = getArguments();
        //Favoritos favoritos = (Favoritos) bundle.getSerializable("favorito");
        Album album = (Album) bundle.getSerializable("favorito");
        ImageView fragmentListadoCancionesImagen = view.findViewById(R.id.fragmentlistadoCancionesImagen);
        TextView fragmentListadoCancionesArtista = view.findViewById(R.id.fragmentListadoCancionesArtista);

        fragmentListadoCancionesArtista.setText(album.getTitle());

        albumController = new AlbumController();
        albumController.getAlbumTracks(album.getId().toString(), new ResultListener<Track>() {
            @Override
            public void onFinish(Track result) {
                TrackAdapter trackAdapter = new TrackAdapter(result.getData(), FragmentListadoCanciones.this);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerViewListaCanciones.setLayoutManager(linearLayoutManager);
                recyclerViewListaCanciones.setAdapter(trackAdapter);

            }
        });
        Glide.with(fragmentListadoCancionesImagen.getContext())
                .load(album.getCover())
                .into(fragmentListadoCancionesImagen);


//        fragmentListadoCancionesImagen.setImageResource(album.getCover());


        return view;


    }

    @Override
    public void onClick(Track track) {

    }

    public interface RecyclerViewListaCancionesListener {
        public void onClick(Track track);
    }

}
