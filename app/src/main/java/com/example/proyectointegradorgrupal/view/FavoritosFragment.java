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

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.controller.AlbumController;
import com.example.proyectointegradorgrupal.dao.ProveedorDeFavoritos;
import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.model.Favoritos;
import com.example.proyectointegradorgrupal.util.ResultListener;
import com.example.proyectointegradorgrupal.view.FavoritosAdapter;

import java.util.List;


public class FavoritosFragment extends Fragment implements AlbumAdapter.AlbumAdapterListener {

    private RecyclerViewFragmentFavoritosListener recyclerViewFragmentFavoritosListener;
    private RecyclerView recyclerViewFavoritos;


    public FavoritosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.recyclerViewFragmentFavoritosListener = (RecyclerViewFragmentFavoritosListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);

        recyclerViewFavoritos = view.findViewById(R.id.fragmentRecycleFavoritos);


        AlbumController albumController = new AlbumController();
        albumController.getAlbumPorSearch(new ResultListener<List<Album>>() {
            @Override
            public void onFinish(List<Album> result) {

                AlbumAdapter albumAdapter = new AlbumAdapter(result, FavoritosFragment.this);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                recyclerViewFavoritos.setLayoutManager(linearLayoutManager);
                recyclerViewFavoritos.setAdapter(albumAdapter);

            }
        });



        return view;
    }

    @Override
    public void onClick(Album album) {
        recyclerViewFragmentFavoritosListener.onClick(album);
    }


    public interface RecyclerViewFragmentFavoritosListener {
        public void onClick(Album album);


    }
}
