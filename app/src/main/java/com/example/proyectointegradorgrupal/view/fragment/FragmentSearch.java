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

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.controller.SearchController;
import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.util.ResultListener;
import com.example.proyectointegradorgrupal.view.MainActivity;
import com.example.proyectointegradorgrupal.view.adapter.TrackAdapter;

import java.util.List;


public class FragmentSearch extends Fragment implements TrackAdapter.TrackAdapterListener {

    private RecyclerView recyclerViewSearchList;
    private FragmentSearchListener fragmentSearchListener;
    private SearchController searchController;
    private TrackAdapter trackAdapter;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.fragmentSearchListener = (FragmentSearchListener) context;

    }

    public FragmentSearch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerViewSearchList = view.findViewById(R.id.fragmentListadoSearch);

        Bundle bundle = getArguments();

        String query = (String) bundle.getSerializable(MainActivity.QUERY);


        searchController = new SearchController(getContext());
        searchController.getSearch(query, new ResultListener<Track>() {
            @Override
            public void onFinish(Track result) {
                trackAdapter = new TrackAdapter(result.getData(), FragmentSearch.this);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerViewSearchList.setLayoutManager(linearLayoutManager);
                recyclerViewSearchList.setAdapter(trackAdapter);

            }
        });


        return view;
    }


    @Override
    public void onClick(List<Track> trackList, int position) {
        fragmentSearchListener.onClickTrackDesdeSearch(trackList, position);
    }



    public interface FragmentSearchListener {


        public void onClickTrackDesdeSearch(List<Track> trackList, int position);

    }
}