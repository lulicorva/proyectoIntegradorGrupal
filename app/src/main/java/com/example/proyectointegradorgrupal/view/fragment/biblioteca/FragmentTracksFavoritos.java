package com.example.proyectointegradorgrupal.view.fragment.biblioteca;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proyectointegradorgrupal.LoginActivity;
import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.controller.DatosUsuariosController;
import com.example.proyectointegradorgrupal.model.DatosUsuario;
import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.util.ResultListener;
import com.example.proyectointegradorgrupal.view.adapter.TrackAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;


public class FragmentTracksFavoritos extends Fragment {


    private TrackAdapter trackAdapter;
    private RecyclerView recyclerView;
    private List<Track> trackListFavoritos;
    private FragmentTracksFavoritosListener fragmentTracksFavoritosListener;
    private DatosUsuariosController datosUsuariosController;

    private DatosUsuario datosUsuario;
    private Track deletedTrack;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.fragmentTracksFavoritosListener = (FragmentTracksFavoritosListener) context;
    }

    public FragmentTracksFavoritos() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tracks_favoritos, container, false);


        recyclerView = view.findViewById(R.id.recyclerTracksFavoritos);

        datosUsuariosController = new DatosUsuariosController(getContext());
        datosUsuariosController.getDatosUsuario(new ResultListener<DatosUsuario>() {
            @Override
            public void onFinish(DatosUsuario result) {
                datosUsuario = result;
                if (result.getTracksFavoritos() != null) {

                    trackListFavoritos = result.getTracksFavoritos();
                    trackAdapter = new TrackAdapter(trackListFavoritos, new TrackAdapter.TrackAdapterListener() {
                        @Override
                        public void onClick(List<Track> trackList, int position) {
                            fragmentTracksFavoritosListener.onClickTrackFavoritos(trackList, position);
                        }

                    });

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(trackAdapter);


                } else {

                    // Toast.makeText(getActivity(), "Aun no tienes favoritos", Toast.LENGTH_SHORT).show();

                }
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        return view;
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN |
            ItemTouchHelper.START |
            ItemTouchHelper.END,
            ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();

            deletedTrack = trackListFavoritos.get(position);
            trackListFavoritos.remove(deletedTrack);

            trackAdapter.notifyItemRemoved(position);
            Snackbar.make(recyclerView, "Track eliminado", BaseTransientBottomBar.LENGTH_LONG)
                    .setAction("Deshacer", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            trackListFavoritos.add(position, deletedTrack);
                            trackAdapter.notifyItemInserted(position);

                            datosUsuariosController.setDatosUsuario(datosUsuario, new ResultListener<DatosUsuario>() {
                                @Override
                                public void onFinish(DatosUsuario result) {
                                    Toast.makeText(getActivity(), "Oleee", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }).show();

            datosUsuariosController.setDatosUsuario(datosUsuario, new ResultListener<DatosUsuario>() {
                @Override
                public void onFinish(DatosUsuario result) {
                    Toast.makeText(getActivity(), "Lista Actualizada", Toast.LENGTH_SHORT).show();
                }
            });

        }
    };


    public interface FragmentTracksFavoritosListener {


        public void onClickTrackFavoritos(List<Track> trackList, int position);
    }
}