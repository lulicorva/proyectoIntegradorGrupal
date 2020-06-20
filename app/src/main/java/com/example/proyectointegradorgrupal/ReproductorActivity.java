package com.example.proyectointegradorgrupal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.proyectointegradorgrupal.model.Track;

import org.w3c.dom.Text;

import java.io.IOException;

public class ReproductorActivity extends AppCompatActivity {


    private MediaPlayer mediaPlayer;

    private ImageView imageViewPlayPause;
    private TextView currentTime;
    private TextView duration;
    private SeekBar seekBar;
    private Handler handler = new Handler();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);

        imageViewPlayPause = findViewById(R.id.reproductorActivityPlayPause);
        currentTime = findViewById(R.id.reproductorActivityCurrentTime);
        duration = findViewById(R.id.reproductorActivityDuration);
        seekBar = findViewById(R.id.reproductorActivitySeekBar);


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


    }


    private void prepareMediaPlayer() {
        try {

            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            Track track = (Track) bundle.get("track");
            final String preview = track.getPreview();
            Uri uri = Uri.parse(preview);


            //mediaPlayer.setDataSource(preview);
            mediaPlayer.setDataSource(this, uri);
            mediaPlayer.prepare();
            long durationMediaPlayer = mediaPlayer.getDuration();
            duration.setText(millisecondsToTimer(durationMediaPlayer));

        } catch (Exception e) {
            Toast.makeText(this, "Algo salio mal", Toast.LENGTH_SHORT).show();
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
 * */
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
