package com.example.proyectointegradorgrupal.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.view.MainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomNavigationFragment extends Fragment{

    private BottomNavigationView bottomNavigationView;
    private FragmentBottomNavigationListener fragmentBottomNavigationListener;

    public BottomNavigationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.fragmentBottomNavigationListener = (FragmentBottomNavigationListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom_navigation, container, false);

        bottomNavigationView = view.findViewById(R.id.fragmentBottomNavigation);

        //todo configurar esto en la main
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menuInicio:
                        Intent i = new Intent(getContext(), MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.menuTuBiblioteca:
                        fragmentBottomNavigationListener.onClickTuBiblioteca();
                        Toast.makeText(getContext(), "Tu Biblioteca", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuPerfil:
                        fragmentBottomNavigationListener.onCLickPerfil();
                        Toast.makeText(getContext(), "Mi Perfil", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });



        return view;
    }

    public interface FragmentBottomNavigationListener{
        public void onClickTuBiblioteca();
        public void onCLickPerfil();

    }


}
