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


public class FragmentPrincipalPrueba extends Fragment implements AlbumAdapter.AlbumAdapterListener, PlaylistAdapter.PlaylistAdapterListener, RecomendadosAdapter.RecomendadosAdapterListener {


    private RecyclerView recyclerViewFavoritosPRUEBA;
    private RecyclerView recyclerViewPlaylistPRUEBA;
    private RecyclerView recyclerViewRecomendadosPRUEBA;

    private FragmentPrincipalListener fragmentPrincipalListener;

    public FragmentPrincipalPrueba() {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_principal_prueba, container, false);


        //FRAGMENT FAVORITOS (ALBUM)
        recyclerViewFavoritosPRUEBA = view.findViewById(R.id.fragmentRecycleFavoritosPRUEBA);
        AlbumController albumController = new AlbumController();
        albumController.getAlbumPorSearch("Beatles", new ResultListener<List<Album>>() {
            @Override
            public void onFinish(List<Album> result) {

                AlbumAdapter albumAdapter = new AlbumAdapter(result, FragmentPrincipalPrueba.this);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                recyclerViewFavoritosPRUEBA.setLayoutManager(linearLayoutManager);
                recyclerViewFavoritosPRUEBA.setAdapter(albumAdapter);

            }
        });


        //FRAGMENT PLAYLIST
        recyclerViewPlaylistPRUEBA = view.findViewById(R.id.fragmentRecyclePlaylistPRUEBA);


        PlaylistController playlistController = new PlaylistController();
        playlistController.getPlaylistPorSearch(new ResultListener<List<Playlist>>() {
            @Override
            public void onFinish(List<Playlist> result) {
                PlaylistAdapter playlistAdapter = new PlaylistAdapter(result, FragmentPrincipalPrueba.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                recyclerViewPlaylistPRUEBA.setLayoutManager(linearLayoutManager);
                recyclerViewPlaylistPRUEBA.setAdapter(playlistAdapter);

            }
        });

        //FRAGMENT RECOMENDADOS (SIGUE HARDCODEADO)
        List<Recomendados> recomendadosList = RecomendadosDao.getRecomendados();

        recyclerViewRecomendadosPRUEBA = view.findViewById(R.id.fragmentRecycleRecomendadosPRUEBA);

        RecomendadosAdapter recomendadosAdapter = new RecomendadosAdapter(recomendadosList, FragmentPrincipalPrueba.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);

        recyclerViewRecomendadosPRUEBA.setLayoutManager(linearLayoutManager);
        recyclerViewRecomendadosPRUEBA.setAdapter(recomendadosAdapter);


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