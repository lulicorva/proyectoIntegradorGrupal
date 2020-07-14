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
import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.model.DatosUsuario;
import com.example.proyectointegradorgrupal.model.Playlist;
import com.example.proyectointegradorgrupal.util.ResultListener;
import com.example.proyectointegradorgrupal.view.adapter.AlbumAdapter;
import com.example.proyectointegradorgrupal.view.adapter.PlaylistAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;


public class FragmentPLaylistsFavoritos extends Fragment {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private RecyclerView recyclerView;
    private PlaylistAdapter playlistAdapter;
    private List<Playlist> playlistFavoritosList;
    private FragmentPlaylistsFavoritosListener fragmentPlaylistsFavoritosListener;
    private DatosUsuariosController datosUsuariosController;

    private DatosUsuario datosUsuario;
    private Playlist playlistDeleted;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.fragmentPlaylistsFavoritosListener = (FragmentPlaylistsFavoritosListener) context;
    }

    public FragmentPLaylistsFavoritos() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_playlists_favoritos, container, false);



        recyclerView = view.findViewById(R.id.recyclerPlaylistsFavoritos);

        datosUsuariosController = new DatosUsuariosController(getContext());
        datosUsuariosController.getDatosUsuario(new ResultListener<DatosUsuario>() {
            @Override
            public void onFinish(DatosUsuario result) {
                if (result.getPlaylistsFavoritos() != null) {

                    datosUsuario = result;
                    playlistFavoritosList = result.getPlaylistsFavoritos();
                    playlistAdapter = new PlaylistAdapter(playlistFavoritosList, new PlaylistAdapter.PlaylistAdapterListener() {
                        @Override
                        public void onClick(Playlist playlist) {
                            fragmentPlaylistsFavoritosListener.onClickPlaylistFavorito(playlist);
                        }
                    }, R.layout.celda_playlist_favoritos);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(playlistAdapter);


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

            playlistDeleted = playlistFavoritosList.get(position);
            playlistFavoritosList.remove(playlistDeleted);

            playlistAdapter.notifyItemRemoved(position);
            Snackbar.make(recyclerView, "Playlist eliminado", BaseTransientBottomBar.LENGTH_LONG)
                    .setAction("Deshacer", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            playlistFavoritosList.add(position, playlistDeleted);
                            playlistAdapter.notifyItemInserted(position);

                            datosUsuariosController.setDatosUsuario(datosUsuario, new ResultListener<DatosUsuario>() {
                                @Override
                                public void onFinish(DatosUsuario result) {

                                }
                            });

                        }
                    }).show();

            datosUsuariosController.setDatosUsuario(datosUsuario, new ResultListener<DatosUsuario>() {
                @Override
                public void onFinish(DatosUsuario result) {

                }
            });

        }
    };

    public interface FragmentPlaylistsFavoritosListener{
        public void onClickPlaylistFavorito(Playlist playlist);
    }
}