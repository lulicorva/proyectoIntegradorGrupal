package com.example.proyectointegradorgrupal.view.fragment.biblioteca;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.view.adapter.ViewPagerAdapterTuBiblioteca;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class FragmentTuBiblioteca extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private FirebaseFirestore db;

    private ViewPager viewPager;
    private ViewPagerAdapterTuBiblioteca viewPagerAdapter;

    public FragmentTuBiblioteca() {

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tu_biblioteca, container, false);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();


        viewPager = view.findViewById(R.id.fragmentTuBibliotecaViewPager);

        FragmentTracksFavoritos fragmentTracksFavoritos = new FragmentTracksFavoritos();
        FragmentAlbumsFavoritos fragmentAlbumsFavoritos = new FragmentAlbumsFavoritos();
        FragmentPLaylistsFavoritos fragmentPLaylistsFavoritos = new FragmentPLaylistsFavoritos();

        viewPagerAdapter = new ViewPagerAdapterTuBiblioteca(getActivity().getSupportFragmentManager(), 2, fragmentTracksFavoritos, fragmentAlbumsFavoritos, fragmentPLaylistsFavoritos);
        viewPager.setAdapter(viewPagerAdapter);



        return view;
    }
}