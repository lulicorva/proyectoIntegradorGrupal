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
import com.example.proyectointegradorgrupal.util.ResultListener;
import com.example.proyectointegradorgrupal.view.adapter.AlbumAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;


public class FragmentAlbumsFavoritos extends Fragment {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private RecyclerView recyclerView;
    private AlbumAdapter albumAdapter;
    private List<Album> albumsFavoritosList;
    private FragmentAlbumsFavoritosListener fragmentAlbumsFavoritosListener;
    private DatosUsuariosController datosUsuariosController;

    private DatosUsuario datosUsuario;
    private Album deletedAlbum;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.fragmentAlbumsFavoritosListener = (FragmentAlbumsFavoritosListener) context;
    }

    public FragmentAlbumsFavoritos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_albums_favoritos, container, false);


        recyclerView = view.findViewById(R.id.recyclerAlbumsFavoritos);


        datosUsuariosController = new DatosUsuariosController(getContext());
        datosUsuariosController.getDatosUsuario(new ResultListener<DatosUsuario>() {
            @Override
            public void onFinish(DatosUsuario result) {
                if (result.getAlbumesFavoritos() != null) {

                    datosUsuario = result;
                    albumsFavoritosList = result.getAlbumesFavoritos();
                    albumAdapter = new AlbumAdapter(albumsFavoritosList, new AlbumAdapter.AlbumAdapterListener() {
                        @Override
                        public void onClick(Album album) {
                            fragmentAlbumsFavoritosListener.onClickAlbumFavorito(album);
                        }

                    }, R.layout.celda_albums_favoritos);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(albumAdapter);


                } else {

                    //Toast.makeText(getActivity(), "Aun no tienes favoritos", Toast.LENGTH_SHORT).show();

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

            deletedAlbum = albumsFavoritosList.get(position);
            albumsFavoritosList.remove(deletedAlbum);

            albumAdapter.notifyItemRemoved(position);
            Snackbar.make(recyclerView, "Album eliminado", BaseTransientBottomBar.LENGTH_LONG)
                    .setAction("Deshacer", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            albumsFavoritosList.add(position, deletedAlbum);
                            albumAdapter.notifyItemInserted(position);

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

    public interface FragmentAlbumsFavoritosListener {
        public void onClickAlbumFavorito(Album album);
    }
}