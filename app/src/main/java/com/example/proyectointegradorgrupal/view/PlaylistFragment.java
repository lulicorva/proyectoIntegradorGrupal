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

import com.example.proyectointegradorgrupal.dao.PlaylistDao;
import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.model.Playlist;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistFragment extends Fragment implements PlaylistAdapter.PlaylistAdapterListener{

    private RecyclerViewFragmentPlaylistListener recyclerViewFragmentPlaylistListener;

    public PlaylistFragment() {
        // Required empty public constructor
    }

    //solucionar

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playlist_fragment, container, false);

        List<Playlist> playlistList = PlaylistDao.getPlaylist();

        RecyclerView recyclerView = view.findViewById(R.id.fragmentRecyclePlaylist);
        PlaylistAdapter playlistAdapter = new PlaylistAdapter(playlistList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(playlistAdapter);


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
