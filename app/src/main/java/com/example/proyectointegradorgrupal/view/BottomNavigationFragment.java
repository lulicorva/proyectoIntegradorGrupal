package com.example.proyectointegradorgrupal.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proyectointegradorgrupal.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomNavigationFragment extends Fragment {

    private BottomNavigationView bottomNavigationView;

    public BottomNavigationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom_navigation, container, false);

        bottomNavigationView = view.findViewById(R.id.fragmentBottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menuInicio:
                        Toast.makeText(getContext(), "Inicio", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuBuscar:
                        Toast.makeText(getContext(), "Buscar", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuTuBiblioteca:
                        Toast.makeText(getContext(), "Tu Biblioteca", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        return view;
    }
}
