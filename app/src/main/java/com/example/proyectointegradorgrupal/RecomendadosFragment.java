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
public class RecomendadosFragment extends Fragment {

    public RecomendadosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recomendados, container, false);

        List<Recomendados> recomendadosList = ProveedorDeRecomendados.getRecomendados();

        RecyclerView recyclerViewRecomendados = view.findViewById(R.id.fragmentRecycleRecomendados);

        RecomendadosAdapter recomendadosAdapter = new RecomendadosAdapter(recomendadosList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);

        recyclerViewRecomendados.setLayoutManager(linearLayoutManager);
        recyclerViewRecomendados.setAdapter(recomendadosAdapter);


        return view;
    }
}
