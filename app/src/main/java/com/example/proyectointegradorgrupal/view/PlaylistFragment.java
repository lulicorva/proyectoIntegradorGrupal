package com.example.proyectointegradorgrupal.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectointegradorgrupal.controller.PlaylistController;
import com.example.proyectointegradorgrupal.dao.PlaylistDao;
import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.dao.ProveedorDeFavoritos;
import com.example.proyectointegradorgrupal.model.ConteinerPlayList;
import com.example.proyectointegradorgrupal.model.Favoritos;
import com.example.proyectointegradorgrupal.model.Playlist;
import com.example.proyectointegradorgrupal.util.ResultListener;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistFragment extends Fragment implements PlaylistAdapter.PlaylistAdapterListener {

    private RecyclerViewFragmentPlaylistListener recyclerViewFragmentPlaylistListener;

    private RecyclerView recyclerView;


    public PlaylistFragment() {
        // Required empty public constructor
    }


    //solucionar

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playlist_fragment, container, false);


        recyclerView = view.findViewById(R.id.fragmentRecyclePlaylist);


        PlaylistController playlistController = new PlaylistController();
        playlistController.getPlaylistPorSearch(new ResultListener<List<Playlist>>() {
            @Override
            public void onFinish(List<Playlist> result) {
                PlaylistAdapter playlistAdapter = new PlaylistAdapter(result, PlaylistFragment.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(playlistAdapter);

            }
        });


        return view;

    }

    @Override
    public void onClick(Playlist playlist) {
        recyclerViewFragmentPlaylistListener.onClick(playlist);
    }

    public interface RecyclerViewFragmentPlaylistListener {
        public void onClick(Playlist playlist);
    }
}
