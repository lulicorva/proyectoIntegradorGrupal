package com.example.proyectointegradorgrupal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class playlist_fragment extends Fragment {

    public playlist_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playlist_fragment, container, false);

        List<Playlist> playlistList = ProveedorDePlaylist.getPlaylist();

        RecyclerView recyclerView = view.findViewById(R.id.fragmentRecyclePlaylist);
        PlaylistAdapter playlistAdapter = new PlaylistAdapter(playlistList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(playlistAdapter);


        return view;


    }
}
