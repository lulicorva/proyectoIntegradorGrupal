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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.controller.PlaylistController;
import com.example.proyectointegradorgrupal.model.Playlist;
import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.util.ResultListener;
import com.example.proyectointegradorgrupal.view.MainActivity;
import com.example.proyectointegradorgrupal.view.adapter.TrackAdapter;
import com.example.proyectointegradorgrupal.view.adapter.ViewPagerAdapter;


public class FragmentPlaylistTracks extends Fragment implements TrackAdapter.TrackAdapterListener {

    private RecyclerView recyclerView;
    private PlaylistController playlistController;
    private FragmentPlaylistTracksListener fragmentPlaylistTracksListener;
    private TrackAdapter trackAdapter;

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


        Bundle bundle = getArguments();

        Playlist playlist = (Playlist) bundle.getSerializable(MainActivity.PLAYLIST);

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



        return view;
    }

    @Override
    public void onClick(Track track) {
        fragmentPlaylistTracksListener.onClickTrackDesdePlaylist(track);
    }

    public interface  FragmentPlaylistTracksListener{
        public void onClickTrackDesdePlaylist(Track track);
    }
}