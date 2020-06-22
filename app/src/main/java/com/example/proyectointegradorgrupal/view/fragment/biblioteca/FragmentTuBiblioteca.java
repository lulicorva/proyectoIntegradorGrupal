package com.example.proyectointegradorgrupal.view.fragment.biblioteca;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.view.adapter.ViewPagerAdapter;
import com.example.proyectointegradorgrupal.view.fragment.FragmentAlbumTracks;
import com.example.proyectointegradorgrupal.view.fragment.FragmentDetalleCancion;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class FragmentTuBiblioteca extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private FirebaseFirestore db;

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

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

        FragmentDetalleCancion fragmentDetalleCancion1 = new FragmentDetalleCancion();
        FragmentDetalleCancion fragmentDetalleCancion2 = new FragmentDetalleCancion();

        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), 2, fragmentDetalleCancion1, fragmentDetalleCancion2);
        viewPager.setAdapter(viewPagerAdapter);



        return view;
    }
}