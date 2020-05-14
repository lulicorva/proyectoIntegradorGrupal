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
import com.example.proyectointegradorgrupal.dao.ProveedorDeFavoritos;
import com.example.proyectointegradorgrupal.model.Favoritos;
import com.example.proyectointegradorgrupal.view.FavoritosAdapter;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends Fragment implements FavoritosAdapter.FavoritosAdapterListener {

    private RecyclerViewFragmentFavoritosListener recyclerViewFragmentFavoritosListener;

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

        RecyclerView recyclerViewFavoritos = view.findViewById(R.id.fragmentRecycleFavoritos);

        List<Favoritos> favoritosList = ProveedorDeFavoritos.getFavoritos();
        FavoritosAdapter favoritosAdapter = new FavoritosAdapter(favoritosList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerViewFavoritos.setLayoutManager(linearLayoutManager);
        recyclerViewFavoritos.setAdapter(favoritosAdapter);


        return view;
    }

    @Override
    public void onClick(Favoritos favoritos) {
        recyclerViewFragmentFavoritosListener.onClick(favoritos);
    }

    public interface RecyclerViewFragmentFavoritosListener {
        public void onClick(Favoritos favoritos);


    }
}
