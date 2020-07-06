package com.example.proyectointegradorgrupal.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;


public class ReproductorSingleton {

    private static MediaPlayer mediaPlayer;

    private static ReproductorSingleton INSTANCE = null;


    private synchronized static void createInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReproductorSingleton();
        }
    }

    public static ReproductorSingleton getInstance() {
        if (INSTANCE == null) {
            createInstance();
            mediaPlayer = new MediaPlayer();
        }
        return INSTANCE;
    }

    private ReproductorSingleton() {

    }


    /**
     * Se setea el media player con los datos de la cancion
     */
    public void prepareMediaPlayer(Context context, Uri uriTrack) {
        try {

            getInstance();
            mediaPlayer.setDataSource(context, uriTrack);
            mediaPlayer.prepare();


        } catch (Exception e) {
            // Toast.makeText(getActivity(), "Algo salio mal", Toast.LENGTH_SHORT).show();
        }

    }



    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setNewMediaPlayer() {
        ReproductorSingleton.mediaPlayer = new MediaPlayer();
    }
}

