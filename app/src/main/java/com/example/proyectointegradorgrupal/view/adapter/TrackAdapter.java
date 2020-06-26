package com.example.proyectointegradorgrupal.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.model.Track;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolderTrack> {

    private List<Track> trackList;
    private TrackAdapterListener trackAdapterListener;

    public TrackAdapter(List<Track> trackList, TrackAdapterListener listener) {
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
        private MaterialTextView albumCancion;

        public ViewHolderTrack(@NonNull View itemView) {
            super(itemView);

            nombreTrack = itemView.findViewById(R.id.celdaNombreCancion);
            duracion = itemView.findViewById(R.id.celdaDuracionCancion);
            albumCancion = itemView.findViewById(R.id.celdaAlbumCancion);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Track track = trackList.get(getAdapterPosition());
                    trackAdapterListener.onClick(track);
                    trackAdapterListener.onClick(trackList);
                }
            });

        }

        public void onBind(Track track) {

            // Double duracionDouble = track.getDuration();

            String totalTime = segundosTotalesAMinutosYSegundos(track.getDuration());

            nombreTrack.setText(track.getTitleShort());
            duracion.setText(totalTime);

            if (track.getAlbum() != null) {
                albumCancion.setText(track.getAlbum().getTitle());

            }
        }

    }


    private String segundosTotalesAMinutosYSegundos(Double tsegundos) {
        String tiempoTotal;

        int horas = (int) (tsegundos / 3600);
        int minutos = (int) ((tsegundos - horas * 3600) / 60);
        int segundos = (int) (tsegundos - (horas * 3600 + minutos * 60));

        if (segundos < 10) {
            tiempoTotal = minutos + ":0" + segundos;
        } else {

            tiempoTotal = minutos + ":" + segundos;
        }
        return tiempoTotal;
    }


    public interface TrackAdapterListener {
        public void onClick(List<Track> trackList);
        public void onClick(Track track);
    }


}
