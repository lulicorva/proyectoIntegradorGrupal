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

import com.example.proyectointegradorgrupal.dao.RecomendadosDao;
import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.model.Recomendados;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecomendadosFragment extends Fragment implements RecomendadosAdapter.RecomendadosAdapterListener{

    private RecyclerViewRecomendadosListener recyclerViewRecomendadosListener;

    public RecomendadosFragment() {
        // Required empty public constructor
    }

    //solucionar

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recomendados, container, false);

        List<Recomendados> recomendadosList = RecomendadosDao.getRecomendados();

        RecyclerView recyclerViewRecomendados = view.findViewById(R.id.fragmentRecycleRecomendados);

        RecomendadosAdapter recomendadosAdapter = new RecomendadosAdapter(recomendadosList,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);

        recyclerViewRecomendados.setLayoutManager(linearLayoutManager);
        recyclerViewRecomendados.setAdapter(recomendadosAdapter);


        return view;
    }

    @Override
    public void onClick(Recomendados recomendados) {
        recyclerViewRecomendadosListener.onClick(recomendados);

    }

    public interface RecyclerViewRecomendadosListener{
        public void onClick(Recomendados recomendados);
    }
}
