package com.example.proyectointegradorgrupal.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.model.Album;
import com.example.proyectointegradorgrupal.model.Favoritos;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolderAlbum> {

    private List<Album> albums;
    private AlbumAdapterListener albumAdapterListener;

    public AlbumAdapter(List<Album> albums, AlbumAdapterListener listener) {
        this.albums = albums;
        this.albumAdapterListener = listener;
    }

    @NonNull
    @Override
    public ViewHolderAlbum onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.celda_favoritos, parent, false);

        return new ViewHolderAlbum(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolderAlbum holder, int position) {
        Album album = this.albums.get(position);
        holder.onBind(album);
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    protected class ViewHolderAlbum extends RecyclerView.ViewHolder {

        private CircleImageView circleImageViewAlbum;
        private TextView textViewAlbum;


        public ViewHolderAlbum(@NonNull View itemView) {
            super(itemView);
            circleImageViewAlbum = itemView.findViewById(R.id.favoritosImageView);
            textViewAlbum = itemView.findViewById(R.id.favoritosTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Album album = albums.get(getAdapterPosition());
                   albumAdapterListener.onClick(album);

                }
            });


        }

        public void onBind(Album album) {
            Glide.with(textViewAlbum.getContext())
                    .load(album.getCover())
                    .into(circleImageViewAlbum);

            textViewAlbum.setText(album.getTitle());

        }
    }

    public interface AlbumAdapterListener {
        public void onClick(Album album);

    }

}

