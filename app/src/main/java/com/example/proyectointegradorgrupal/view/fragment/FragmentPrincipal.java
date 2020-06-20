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

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.controller.AlbumController;
import com.example.proyectointegradorgrupal.controller.PlaylistController;
import com.example.proyectointegradorgrupal.dao.RecomendadosDao;
import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.model.Playlist;
import com.example.proyectointegradorgrupal.model.Recomendados;
import com.example.proyectointegradorgrupal.util.ResultListener;
import com.example.proyectointegradorgrupal.view.adapter.AlbumAdapter;
import com.example.proyectointegradorgrupal.view.adapter.PlaylistAdapter;
import com.example.proyectointegradorgrupal.view.adapter.RecomendadosAdapter;

import java.util.List;


public class FragmentPrincipal extends Fragment implements AlbumAdapter.AlbumAdapterListener, PlaylistAdapter.PlaylistAdapterListener, RecomendadosAdapter.RecomendadosAdapterListener {


    private RecyclerView recyclerViewFavoritos;
    private RecyclerView recyclerViewPlaylist;
    private RecyclerView recyclerViewRecomendados;

    private FragmentPrincipalListener fragmentPrincipalListener;

    public FragmentPrincipal() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.fragmentPrincipalListener = (FragmentPrincipalListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_principal, container, false);


        /**
         * FAVORITOS (ALBUM)
         * */
        recyclerViewFavoritos = view.findViewById(R.id.fragmentRecycleFavoritos);
        AlbumController albumController = new AlbumController();
        albumController.getAlbumPorSearch("Red Hot Chilli Peppers", new ResultListener<List<Album>>() {
            @Override
            public void onFinish(List<Album> result) {

                AlbumAdapter albumAdapter = new AlbumAdapter(result, FragmentPrincipal.this);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                recyclerViewFavoritos.setLayoutManager(linearLayoutManager);
                recyclerViewFavoritos.setAdapter(albumAdapter);

            }
        });


        /**
         * PLAYLIST
         * */
        recyclerViewPlaylist = view.findViewById(R.id.fragmentRecyclePlaylist);
        PlaylistController playlistController = new PlaylistController();
        playlistController.getPlaylistPorSearch("Cumbia", new ResultListener<List<Playlist>>() {
            @Override
            public void onFinish(List<Playlist> result) {
                PlaylistAdapter playlistAdapter = new PlaylistAdapter(result, FragmentPrincipal.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                recyclerViewPlaylist.setLayoutManager(linearLayoutManager);
                recyclerViewPlaylist.setAdapter(playlistAdapter);

            }
        });

        /**
         * RECOMENDADOS (SIGUE HARDCODEADO)
         * */
        List<Recomendados> recomendadosList = RecomendadosDao.getRecomendados();

        recyclerViewRecomendados = view.findViewById(R.id.fragmentRecycleRecomendados);

        RecomendadosAdapter recomendadosAdapter = new RecomendadosAdapter(recomendadosList, FragmentPrincipal.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);

        recyclerViewRecomendados.setLayoutManager(linearLayoutManager);
        recyclerViewRecomendados.setAdapter(recomendadosAdapter);


        return view;
    }

    @Override
    public void onClick(Album album) {
        fragmentPrincipalListener.onClick(album);
    }

    @Override
    public void onClick(Playlist playlist) {
        fragmentPrincipalListener.onClick(playlist);
    }

    @Override
    public void onClick(Recomendados recomendados) {
        fragmentPrincipalListener.onClick(recomendados);
    }

    public interface FragmentPrincipalListener {
        public void onClick(Album album);

        public void onClick(Playlist playlist);

        public void onClick(Recomendados recomendados);

    }
}