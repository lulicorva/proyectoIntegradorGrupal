package com.example.proyectointegradorgrupal.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.model.Playlist;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

//import static com.example.proyectointegradorgrupal.model.ConteinerPlayList.listaDePlaylist;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolderPlaylist> {

    private List<Playlist> playlistList;
    private PlaylistAdapterListener playlistAdapterListener;
    private int celda_playlist;

    public PlaylistAdapter(List<Playlist> playlistList, PlaylistAdapterListener listener, int celda_playlist) {
        this.playlistList = playlistList;
        this.playlistAdapterListener = listener;
        this.celda_playlist = celda_playlist;

    }



    @NonNull
    @Override
    public ViewHolderPlaylist onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(celda_playlist, parent, false);

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

    public void SetPlaylistList(List<Playlist> playlistList){
        this.playlistList = playlistList;
        notifyDataSetChanged();

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
                    Glide.with(textViewPlaylist.getContext())
                    .load(playlist.getPictureXL())
                    .into(circleImageViewPlaylist);

                    textViewPlaylist.setText(playlist.getTitle());
        }
    }

    public interface PlaylistAdapterListener {
        public void onClick(Playlist playlist);
    }

}

