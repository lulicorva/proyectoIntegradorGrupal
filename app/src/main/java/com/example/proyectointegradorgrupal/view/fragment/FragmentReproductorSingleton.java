package com.example.proyectointegradorgrupal.view.fragment;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.controller.AlbumController;
import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.view.ReproductorSingleton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;


public class FragmentReproductorSingleton extends Fragment {


    private ImageView imageViewPlayPause;
    private TextView currentTime;
    private TextView duration;
    private SeekBar seekBar;
    private ImageButton botonFavoritos;
    private MaterialTextView nombreTrack;
    private MaterialTextView nombreArtista;

    private Uri uriTrack;
    private Track track;


    private ReproductorSingleton reproductorSingleton;
    private AlbumController albumController;
    private ArrayList<Track> trackList;
    private ArrayList<String> listPreviews;


    public FragmentReproductorSingleton() {
        // Required empty public constructor
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reproductor_singleton, container, false);


        imageViewPlayPause = view.findViewById(R.id.fragmentReproductorPlayPause);
        currentTime = view.findViewById(R.id.fragmentReproductorCurrentTime);
        duration = view.findViewById(R.id.fragmentReproductorDuration);
        nombreTrack = view.findViewById(R.id.fragmentReproductorNombreTrack);
        nombreArtista = view.findViewById(R.id.fragmentReproductorNombreArtista);
        seekBar = view.findViewById(R.id.fragmentReproductorSeekBar);
        botonFavoritos = view.findViewById(R.id.reproductorFavoritos);


      /*  Bundle bundle = getArguments();
        track = (Track) bundle.get("track");
        final String preview = track.getPreview();
        uriTrack = Uri.parse(preview);
*/

//        nombreArtista.setText(track.getArtist().getName());
        //      nombreTrack.setText(track.getTitleShort());


        uriTrack = Uri.parse("https://cdns-preview-e.dzcdn.net/stream/c-e77d23e0c8ed7567a507a6d1b6a9ca1b-9.mp3");
        seekBar.setMax(100);
        reproductorSingleton = ReproductorSingleton.getInstance();
        reproductorSingleton.setSeekBar(seekBar);
        reproductorSingleton.setContext(getContext());
        reproductorSingleton.setUriTrack(uriTrack);
        reproductorSingleton.prepareMediaPlayer();


        long durationMediaPlayer = reproductorSingleton.getMediaPlayer().getDuration();
        duration.setText(reproductorSingleton.millisecondsToTimer(durationMediaPlayer));

        imageViewPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ReproductorSingleton.getInstance().getMediaPlayer().isPlaying()) {

                    reproductorSingleton.getMediaPlayer().pause();
                    imageViewPlayPause.setImageResource(R.drawable.ic_play_circle_filled);
                    reproductorSingleton.updateSeekBar();
                } else {

                    reproductorSingleton.getMediaPlayer().start();
                    imageViewPlayPause.setImageResource(R.drawable.ic_pause_circle_filled);
                    reproductorSingleton.updateSeekBar();

                }
            }
        });


        /**
         * Esto hace que puedas adelantar la cancion con la barra
         * */
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SeekBar newSeekBar = (SeekBar) v;
                int playPosition = (reproductorSingleton.getMediaPlayer().getDuration() / 100) * newSeekBar.getProgress();
                reproductorSingleton.getMediaPlayer().seekTo(playPosition);
                currentTime.setText(reproductorSingleton.millisecondsToTimer(reproductorSingleton.getMediaPlayer().getCurrentPosition()));

                return false;
            }
        });


        /**
         * Este metodo hace que se almacene la cancion en el buffer
         * */
        reproductorSingleton.getMediaPlayer().setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekBar.setSecondaryProgress(percent);

            }
        });


        return view;
    }

    private Runnable updater = new Runnable() {
        @Override
        public void run() {

             long currentDuration = reproductorSingleton.getMediaPlayer().getCurrentPosition();

             currentTime.setText(reproductorSingleton.millisecondsToTimer(currentDuration));

        }
    };


}

