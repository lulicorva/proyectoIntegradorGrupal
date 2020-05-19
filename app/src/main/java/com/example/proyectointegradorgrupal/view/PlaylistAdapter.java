package com.example.proyectointegradorgrupal.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.model.Playlist;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolderPlaylist> {

    private List<Playlist> playlistList;
    private PlaylistAdapterListener playlistAdapterListener;

    public PlaylistAdapter(List<Playlist> playlistList, PlaylistAdapterListener listener) {
        this.playlistList = playlistList;
        this.playlistAdapterListener = listener;
    }

    @NonNull
    @Override
    public ViewHolderPlaylist onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.celda_playlist, parent, false);

        return new ViewHolderPlaylist(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPlaylist holder, int position) {
        Playlist playlist = this.playlistList.get(position);
        holder.onBind(playlist);
    }

    @Override
    public int getItemCount() {
        return playlistList.size();
    }

    protected class ViewHolderPlaylist extends RecyclerView.ViewHolder{

        private CircleImageView circleImageViewPlaylist;
        private TextView textViewPlaylist;


        public ViewHolderPlaylist(@NonNull View itemView) {
            super(itemView);
            circleImageViewPlaylist = itemView.findViewById(R.id.playlistImageView);
            textViewPlaylist = itemView.findViewById(R.id.playlistTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Playlist playlist = playlistList.get(getAdapterPosition());
                    playlistAdapterListener.onClick(playlist);

                }
            });

        }

        public void onBind(Playlist playlist) {
            circleImageViewPlaylist.setImageResource(playlist.getImagen());
            textViewPlaylist.setText(playlist.getNombre());
        }
    }

    public interface PlaylistAdapterListener {
        public void onClick(Playlist playlist);
    }

}

