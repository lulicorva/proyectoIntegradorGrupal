package com.example.proyectointegradorgrupal.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.widget.SeekBar;

import com.example.proyectointegradorgrupal.model.Track;

public class ReproductorSingleton {

    private static MediaPlayer mediaPlayer;
    private static Handler handler = new Handler();
    private static Uri uriTrack;
    private static Track track;
    private SeekBar seekBar;
    private Context context;

    private static ReproductorSingleton INSTANCE = null;


    private synchronized static void createInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReproductorSingleton();
        }
    }

    public static ReproductorSingleton getInstance() {
        if (INSTANCE == null){
            createInstance();
        mediaPlayer = new MediaPlayer();
        }
        return INSTANCE;
    }

    private ReproductorSingleton() {

    }


    /**
     * Se setea el media player con los datos de la cancion clickeada
     */
    public void prepareMediaPlayer() {
        try {

            getInstance();
            mediaPlayer.setDataSource(context, uriTrack);
            mediaPlayer.prepare();
            long durationMediaPlayer = mediaPlayer.getDuration();
            //duration.setText(millisecondsToTimer(durationMediaPlayer));

        } catch (Exception e) {
            // Toast.makeText(getActivity(), "Algo salio mal", Toast.LENGTH_SHORT).show();
        }

    }

    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            // long currentDuration = mediaPlayer.getCurrentPosition();

            // currentTime.setText(millisecondsToTimer(currentDuration));

        }
    };

    public void updateSeekBar() {
        if (mediaPlayer.isPlaying()) {
            seekBar.setProgress((int) ((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration() * 100));

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

    public Uri getUriTrack() {
        return uriTrack;
    }

    public void setUriTrack(Uri uriTrack) {
        this.uriTrack = uriTrack;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public SeekBar getSeekBar() {
        return seekBar;
    }

    public void setSeekBar(SeekBar seekBar) {
        this.seekBar = seekBar;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }
}

