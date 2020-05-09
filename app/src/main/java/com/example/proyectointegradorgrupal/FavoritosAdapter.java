package com.example.proyectointegradorgrupal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FavoritosAdapter extends RecyclerView.Adapter<FavoritosAdapter.ViewHolderFavoritos> {

    private List<Favoritos> favoritosList;

    public FavoritosAdapter(List<Favoritos> favoritosList) {
        this.favoritosList = favoritosList;
    }

    @NonNull
    @Override
    public ViewHolderFavoritos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.celda_favoritos, parent, false);

        return new ViewHolderFavoritos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFavoritos holder, int position) {
        Favoritos favoritos = this.favoritosList.get(position);
        holder.onBind(favoritos);
    }

    @Override
    public int getItemCount() {
        return favoritosList.size();
    }

    protected class ViewHolderFavoritos extends RecyclerView.ViewHolder{

        private CircleImageView circleImageViewFavoritos;
        private TextView textViewFavoritos;


        public ViewHolderFavoritos(@NonNull View itemView) {
            super(itemView);
            circleImageViewFavoritos = itemView.findViewById(R.id.favoritosImageView);
            textViewFavoritos = itemView.findViewById(R.id.favoritosTextView);



        }

        public void onBind(Favoritos favoritos) {
            circleImageViewFavoritos.setImageResource(favoritos.getImagen());
            textViewFavoritos.setText(favoritos.getNombre());
        }
    }

}

