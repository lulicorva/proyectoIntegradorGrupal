package com.example.proyectointegradorgrupal.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.model.Track;
import com.google.android.material.textview.MaterialTextView;


public class FragmentReproductor extends Fragment {


    private MediaPlayer mediaPlayer;

    private ImageView imageViewPlayPause;
    private TextView currentTime;
    private TextView duration;
    private SeekBar seekBar;
    private MaterialTextView nombreTrack;
    private MaterialTextView nombreArtista;
    private Handler handler = new Handler();
    private Uri uriTrack;


    public FragmentReproductor() {
        // Required empty public constructor
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
             View view = inflater.inflate(R.layout.fragment_reproductor, container, false);

        imageViewPlayPause = view.findViewById(R.id.fragmentReproductorPlayPause);
        currentTime = view.findViewById(R.id.fragmentReproductorCurrentTime);
        duration = view.findViewById(R.id.fragmentReproductorDuration);
        nombreTrack = view.findViewById(R.id.fragmentReproductorNombreTrack);
        nombreArtista = view.findViewById(R.id.fragmentReproductorNombreArtista);
        seekBar = view.findViewById(R.id.fragmentReproductorSeekBar);



        Bundle bundle = getArguments();
        Track track = (Track) bundle.get("track");
        final String preview = track.getPreview();
        uriTrack = Uri.parse(preview);


        nombreArtista.setText(track.getArtist().getName());
        nombreTrack.setText(track.getTitleShort());


        mediaPlayer = new MediaPlayer();
        seekBar.setMax(100);

        imageViewPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    imageViewPlayPause.setImageResource(R.drawable.ic_play_circle_filled);
                } else {
                    mediaPlayer.start();
                    imageViewPlayPause.setImageResource(R.drawable.ic_pause_circle_filled);
                    updateSeekBar();

                }
            }
        });


        prepareMediaPlayer();

        /**
         * Esto hace que puedas adelantar la cancion con la barra
         * */
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SeekBar newSeekBar = (SeekBar) v;
                int playPosition = (mediaPlayer.getDuration() / 100) * newSeekBar.getProgress();
                mediaPlayer.seekTo(playPosition);
                currentTime.setText(millisecondsToTimer(mediaPlayer.getCurrentPosition()));

                return false;
            }
        });


        /**
         * Este metodo hace que se almacene la cancion en el buffer
         * */
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekBar.setSecondaryProgress(percent);

            }
        });


        return view;
    }


    /**
     * Se setea el media player con los datos de la cancion clickeada
     */
    private void prepareMediaPlayer() {
        try {


            mediaPlayer.setDataSource(getContext(), uriTrack);
            mediaPlayer.prepare();
            long durationMediaPlayer = mediaPlayer.getDuration();
            duration.setText(millisecondsToTimer(durationMediaPlayer));

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Algo salio mal", Toast.LENGTH_SHORT).show();
        }

    }

    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentDuration = mediaPlayer.getCurrentPosition();

            currentTime.setText(millisecondsToTimer(currentDuration));

        }
    };

    private void updateSeekBar() {
        if (mediaPlayer.isPlaying()) {
            seekBar.setProgress((int) ((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration() * 100));

            handler.postDelayed(updater, 1000);

        }
    }

    /**
     * Metodo para pasar millisegundos a segundos
     */
    private String millisecondsToTimer(long milliSeconds) {
        String timerStrings;
        String secondsString;


        int seconds = (int) (milliSeconds / 1000);

        if (seconds < 10) {
            secondsString = "0" + seconds;

        } else {
            secondsString = "" + seconds;
        }

        timerStrings = "0" + ":" + secondsString;
        return timerStrings;


    }
}
