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
public class FavoritosFragment extends Fragment {

    public FavoritosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);

        RecyclerView recyclerViewFavoritos = view.findViewById(R.id.fragmentRecycleFavoritos);

        List<Favoritos> favoritosList = ProveedorDeFavoritos.getFavoritos();
        FavoritosAdapter favoritosAdapter = new FavoritosAdapter(favoritosList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerViewFavoritos.setLayoutManager(linearLayoutManager);
        recyclerViewFavoritos.setAdapter(favoritosAdapter);

        return view;


    }
}
