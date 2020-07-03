package com.example.proyectointegradorgrupal.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.controller.AlbumController;
import com.example.proyectointegradorgrupal.controller.PlaylistController;
import com.example.proyectointegradorgrupal.controller.PodcastController;
import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.model.Playlist;
import com.example.proyectointegradorgrupal.model.Podcast;
import com.example.proyectointegradorgrupal.model.PodcastContainer;
import com.example.proyectointegradorgrupal.util.ResultListener;
import com.example.proyectointegradorgrupal.view.adapter.AlbumAdapter;
import com.example.proyectointegradorgrupal.view.adapter.PlaylistAdapter;
import com.example.proyectointegradorgrupal.view.adapter.PodcastAdapter;

import java.util.List;


public class FragmentPrincipal extends Fragment implements AlbumAdapter.AlbumAdapterListener, PlaylistAdapter.PlaylistAdapterListener, PodcastAdapter.PodcastAdapterListener {


    private RecyclerView recyclerViewFavoritos;
    private RecyclerView recyclerViewPlaylist;
    private RecyclerView recyclerViewPodcasts;

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

                AlbumAdapter albumAdapter = new AlbumAdapter(result, FragmentPrincipal.this, R.layout.celda_albums_inicio);

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
                PlaylistAdapter playlistAdapter = new PlaylistAdapter(result, FragmentPrincipal.this, R.layout.celda_playlist_inicio);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                recyclerViewPlaylist.setLayoutManager(linearLayoutManager);
                recyclerViewPlaylist.setAdapter(playlistAdapter);

            }
        });

        /**
         * PODCASTS
         * */
        recyclerViewPodcasts = view.findViewById(R.id.fragmentRecyclerPodcasts);
        PodcastController podcastController = new PodcastController();
        podcastController.getPodcastRecomendados("914a9deafa5340eeaa2859c77f275799", new ResultListener<PodcastContainer>() {
            @Override
            public void onFinish(PodcastContainer result) {
                PodcastAdapter podcastAdapter = new PodcastAdapter(result.getRecommendations(), FragmentPrincipal.this, R.layout.ceda_recomendados_inicio);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                recyclerViewPodcasts.setLayoutManager(linearLayoutManager);
                recyclerViewPodcasts.setAdapter(podcastAdapter);
            }
        });

        return view;
    }

    @Override
    public void onClick(Album album) {
        fragmentPrincipalListener.onClickAlbum(album);
    }

    @Override
    public void onClick(Playlist playlist) {
        fragmentPrincipalListener.onClickPlaylist(playlist);
    }

    @Override
    public void onClick(Podcast podcast) {
        fragmentPrincipalListener.onClickPodcast(podcast);
    }



    public interface FragmentPrincipalListener {
        public void onClickAlbum(Album album);

        public void onClickPlaylist(Playlist playlist);

        public void onClickPodcast(Podcast podcast);

    }
}