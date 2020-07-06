package com.example.proyectointegradorgrupal.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectointegradorgrupal.LoginActivity;
import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.controller.AlbumController;
import com.example.proyectointegradorgrupal.controller.DatosUsuariosController;
import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.util.ResultListener;
import com.example.proyectointegradorgrupal.view.ReproductorSingleton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class FragmentReproductorSingleton extends Fragment {


    private ImageView imageViewPlayPause;
    private ImageView botonNextTrack;
    private ImageView botonPreviousTrack;
    private TextView currentTime;
    private TextView duration;
    private SeekBar seekBar;
    private ImageButton botonFavoritos;
    private MaterialTextView nombreTrack;
    private MaterialTextView nombreArtista;
    private Handler handler = new Handler();

    private Uri uriTrack;
    private Track track;


    private ReproductorSingleton reproductorSingleton;



    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private FragmentReproductorSingletonListener fragmentReproductorSingletonListener;
    private DatosUsuariosController datosUsuariosController;


    public FragmentReproductorSingleton() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.fragmentReproductorSingletonListener = (FragmentReproductorSingletonListener) context;
    }

    public static FragmentReproductorSingleton getFragmentReproductorSingleton(Track track) {

        FragmentReproductorSingleton fragmentReproductorSingleton = new FragmentReproductorSingleton();
        Bundle bundle = new Bundle();
        bundle.putSerializable("track", track);
        fragmentReproductorSingleton.setArguments(bundle);
        return fragmentReproductorSingleton;

    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reproductor_singleton, container, false);



        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        findViewById(view);


        Bundle bundle = getArguments();
        track = (Track) bundle.get("track");


        nombreArtista.setText(track.getArtist().getName());
        nombreTrack.setText(track.getTitleShort());
        seekBar.setMax(100);


        reproductorSingleton = ReproductorSingleton.getInstance();


        long durationMediaPlayer = reproductorSingleton.getMediaPlayer().getDuration();
        duration.setText(millisecondsToTimer(durationMediaPlayer));

        imageViewPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reproductorSingleton.getMediaPlayer().isPlaying()) {
                    handler.removeCallbacks(updater);
                    reproductorSingleton.getMediaPlayer().pause();
                    imageViewPlayPause.setImageResource(R.drawable.ic_play_circle_filled);
                } else {

                    reproductorSingleton.getMediaPlayer().start();
                    imageViewPlayPause.setImageResource(R.drawable.ic_pause_circle_filled);
                    updateSeekBar();

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
                currentTime.setText(millisecondsToTimer(reproductorSingleton.getMediaPlayer().getCurrentPosition()));

                return false;
            }
        });


        /*        *//**
         * Este metodo hace que se almacene la cancion en el buffer
         * */
        reproductorSingleton.getMediaPlayer().setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekBar.setSecondaryProgress(percent);

            }
        });


        botonFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAuth.getCurrentUser() != null) {


                    datosUsuariosController = new DatosUsuariosController(getContext());
                    datosUsuariosController.setTrackFavorito(track, new ResultListener<Track>() {
                        @Override
                        public void onFinish(Track result) {
                            Toast.makeText(getActivity(), "Track gregado a favoritos", Toast.LENGTH_SHORT).show();
                            botonFavoritos.setImageResource(R.drawable.ic_favorite);
                        }
                    });


                } else {
                    Toast.makeText(getActivity(), "Accede a tu cuenta para agregar favoritos", Toast.LENGTH_SHORT).show();
                }


            }
        });

        botonNextTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentReproductorSingletonListener.onClickNext();
            }
        });

        botonPreviousTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentReproductorSingletonListener.onClickPrevious();
            }
        });


        return view;
    }

    public void setPlayPause() {
        if (reproductorSingleton.getMediaPlayer().isPlaying()) {

            imageViewPlayPause.setImageResource(R.drawable.ic_pause_circle_filled);

        } else {

            imageViewPlayPause.setImageResource(R.drawable.ic_play_circle_filled);

        }
    }

    private void findViewById(View view) {
        imageViewPlayPause = view.findViewById(R.id.fragmentReproductorPlayPause);
        currentTime = view.findViewById(R.id.fragmentReproductorCurrentTime);
        duration = view.findViewById(R.id.fragmentReproductorDuration);
        nombreTrack = view.findViewById(R.id.fragmentReproductorNombreTrack);
        nombreArtista = view.findViewById(R.id.fragmentReproductorNombreArtista);
        seekBar = view.findViewById(R.id.fragmentReproductorSeekBar);
        botonFavoritos = view.findViewById(R.id.reproductorFavoritos);
        botonNextTrack = view.findViewById(R.id.botonNextTrack);
        botonPreviousTrack = view.findViewById(R.id.botonPreviousTrack);
    }


    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentDuration = reproductorSingleton.getMediaPlayer().getCurrentPosition();
            currentTime.setText(millisecondsToTimer(currentDuration));

        }
    };


    public void updateSeekBar() {
        if (reproductorSingleton.getMediaPlayer().isPlaying()) {
            seekBar.setProgress((int) ((float) reproductorSingleton.getMediaPlayer().getCurrentPosition() / reproductorSingleton.getMediaPlayer().getDuration() * 100));

            handler.postDelayed(updater, 1000);

        }
    }

    /**
     * Metodo para pasar millisegundos a segundos
     */
    public String millisecondsToTimer(long milliSeconds) {
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


    public interface FragmentReproductorSingletonListener {
        public void onClickNext();
        public void onClickPrevious();
    }

}


