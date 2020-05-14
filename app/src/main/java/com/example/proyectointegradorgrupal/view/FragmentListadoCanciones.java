package com.example.proyectointegradorgrupal.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.model.Favoritos;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListadoCanciones extends Fragment {

    public FragmentListadoCanciones() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listado_canciones, container, false);

        Bundle bundle = getArguments();
        Favoritos favoritos = (Favoritos) bundle.getSerializable("favorito");

        ImageView fragmentListadoCancionesImagen = view.findViewById(R.id.fragmentlistadoCancionesImagen);
        TextView fragmentListadoCancionesArtista = view.findViewById(R.id.fragmentListadoCancionesArtista);

        fragmentListadoCancionesArtista.setText(favoritos.getNombre());
        fragmentListadoCancionesImagen.setImageResource(favoritos.getImagen());


        return view;


    }
}
