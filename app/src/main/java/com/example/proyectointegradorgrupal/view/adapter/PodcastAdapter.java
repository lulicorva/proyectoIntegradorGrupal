package com.example.proyectointegradorgrupal.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.model.Podcast;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PodcastAdapter extends RecyclerView.Adapter<PodcastAdapter.ViewHolderPodcast> {

    private List<Podcast> listaDePodcasts;
    private PodcastAdapterListener podcastAdapterListener;
    private int celda_podcast;

    public PodcastAdapter(List<Podcast> listaDePodcasts, PodcastAdapterListener podcastAdapterListener, int celda_podcast) {
        this.listaDePodcasts = listaDePodcasts;
        this.podcastAdapterListener = podcastAdapterListener;
        this.celda_podcast = celda_podcast;
    }

    @NonNull
    @Override
    public ViewHolderPodcast onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(celda_podcast, parent, false);

        return new ViewHolderPodcast(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPodcast holder, int position) {
        Podcast podcast = this.listaDePodcasts.get(position);
        holder.onBind(podcast);

    }

    @Override
    public int getItemCount() {
        return listaDePodcasts.size();
    }

    protected class ViewHolderPodcast extends RecyclerView.ViewHolder{

        private CircleImageView circleImageViewPodcast;
        private TextView textViewPodcast;


        public ViewHolderPodcast(@NonNull View itemView) {
            super(itemView);
            circleImageViewPodcast = itemView.findViewById(R.id.recomendadosImageView);
            textViewPodcast = itemView.findViewById(R.id.recomendadosTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Podcast podcast = listaDePodcasts.get(getAdapterPosition());
                    podcastAdapterListener.onClick(podcast);

                }
            });

        }

        public void onBind(Podcast podcast) {
            Glide.with(textViewPodcast.getContext())
                    .load(podcast.getThumbnail())
                    .into(circleImageViewPodcast);

            textViewPodcast.setText(podcast.getPodcastTitle());
        }
    }

    public interface PodcastAdapterListener {
        public void onClick(Podcast podcast);
    }
}
