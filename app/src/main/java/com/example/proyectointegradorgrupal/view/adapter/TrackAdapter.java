package com.example.proyectointegradorgrupal.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.model.Track;

import java.util.ArrayList;
import java.util.List;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolderTrack> {

    private ArrayList<Track> trackList;
    private TrackAdapterListener trackAdapterListener;

    public TrackAdapter(ArrayList<Track> trackList, TrackAdapterListener listener) {
        this.trackList = trackList;
        this.trackAdapterListener = listener;
    }


    @NonNull
    @Override
    public ViewHolderTrack onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.track_celda, parent, false);

        return new TrackAdapter.ViewHolderTrack(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTrack holder, int position) {
        Track track = trackList.get(position);
        holder.onBind(track);
    }

    @Override
    public int getItemCount() {
        return this.trackList.size();
    }


    protected class ViewHolderTrack extends RecyclerView.ViewHolder {

        private TextView nombreTrack;
        private TextView duracion;

        public ViewHolderTrack(@NonNull View itemView) {
            super(itemView);

            nombreTrack = itemView.findViewById(R.id.celdaNombreCancion);
            duracion = itemView.findViewById(R.id.celdaDuracionCancion);
        }

        public void onBind(Track track) {

            nombreTrack.setText(track.getTitle());
            duracion.setText(track.getDuration().toString());
        }

    }

    public interface TrackAdapterListener {
        public void onClick(Track track);
    }
}
