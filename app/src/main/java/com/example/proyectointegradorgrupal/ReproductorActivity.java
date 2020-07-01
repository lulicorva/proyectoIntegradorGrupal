package com.example.proyectointegradorgrupal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.view.ReproductorSingleton;
import com.example.proyectointegradorgrupal.view.adapter.ViewPagerAdapterReproductor;
import com.example.proyectointegradorgrupal.view.fragment.FragmentReproductorSingleton;

public class ReproductorActivity extends AppCompatActivity implements FragmentReproductorSingleton.FragmentReproductorSingletonListener {

    private ViewPager viewPager;
    private ViewPagerAdapterReproductor viewPagerAdapterReproductor;

    private int posicionAlScrollear;
    private Track trackList;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        trackList = (Track) bundle.get("trackList");

        //Posicion del track al que hacen click
       int position = bundle.getInt("position");

        viewPager = findViewById(R.id.activityReproductorViewPager);
        viewPagerAdapterReproductor = new ViewPagerAdapterReproductor(getSupportFragmentManager(), 2, trackList.getData());
        viewPager.setAdapter(viewPagerAdapterReproductor);
        viewPager.setCurrentItem(position);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                posicionAlScrollear = position;

                ReproductorSingleton reproductorSingleton = ReproductorSingleton.getInstance();
                reproductorSingleton.getMediaPlayer().pause();

                String preview = trackList.getData().get(position).getPreview();
                Uri uriTrack = Uri.parse(preview);


                reproductorSingleton.setNewMediaPlayer();
                reproductorSingleton.prepareMediaPlayer(ReproductorActivity.this, uriTrack);
                reproductorSingleton.getMediaPlayer().start();

                FragmentReproductorSingleton fragmentReproductorSingleton = (FragmentReproductorSingleton) viewPagerAdapterReproductor.getItem(position);
                fragmentReproductorSingleton.updateSeekBar();
                fragmentReproductorSingleton.setPlayPause();

            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    @Override
    public void onClickNext() {
        viewPager.setCurrentItem(posicionAlScrollear + 1);
        ReproductorSingleton reproductorSingleton = ReproductorSingleton.getInstance();
        reproductorSingleton.getMediaPlayer().pause();

        String preview = trackList.getData().get(posicionAlScrollear + 1).getPreview();
        Uri uriTrack = Uri.parse(preview);


        reproductorSingleton.setNewMediaPlayer();
        reproductorSingleton.prepareMediaPlayer(ReproductorActivity.this, uriTrack);
        reproductorSingleton.getMediaPlayer().start();

        FragmentReproductorSingleton fragmentReproductorSingleton = (FragmentReproductorSingleton) viewPagerAdapterReproductor.getItem(posicionAlScrollear + 1);
        fragmentReproductorSingleton.updateSeekBar();
        fragmentReproductorSingleton.setPlayPause();

    }

    @Override
    public void onClickPrevious() {
        viewPager.setCurrentItem(posicionAlScrollear - 1);

        ReproductorSingleton reproductorSingleton = ReproductorSingleton.getInstance();
        reproductorSingleton.getMediaPlayer().pause();

        String preview = trackList.getData().get(posicionAlScrollear - 1).getPreview();
        Uri uriTrack = Uri.parse(preview);


        reproductorSingleton.setNewMediaPlayer();
        reproductorSingleton.prepareMediaPlayer(ReproductorActivity.this, uriTrack);
        reproductorSingleton.getMediaPlayer().start();

        FragmentReproductorSingleton fragmentReproductorSingleton = (FragmentReproductorSingleton) viewPagerAdapterReproductor.getItem(posicionAlScrollear - 1);
        fragmentReproductorSingleton.updateSeekBar();
        fragmentReproductorSingleton.setPlayPause();


    }
}
