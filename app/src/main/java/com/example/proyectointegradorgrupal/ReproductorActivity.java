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

public class ReproductorActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapterReproductor viewPagerAdapter;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final Track trackList = (Track) bundle.get("trackList");

        //Posicion del track al que hacen click
        int position = bundle.getInt("position");

        viewPager = findViewById(R.id.activityReproductorViewPager);
        viewPagerAdapter = new ViewPagerAdapterReproductor(getSupportFragmentManager(), 2, trackList.getData());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(position);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {



                ReproductorSingleton reproductorSingleton = ReproductorSingleton.getInstance();
                reproductorSingleton.getMediaPlayer().pause();

                String preview = trackList.getData().get(position).getPreview();
                Uri uriTrack = Uri.parse(preview);


                reproductorSingleton.setNewMediaPlayer();
                reproductorSingleton.prepareMediaPlayer(ReproductorActivity.this, uriTrack);
                reproductorSingleton.getMediaPlayer().start();

                FragmentReproductorSingleton fragmentReproductorSingleton = (FragmentReproductorSingleton) viewPagerAdapter.getItem(position);
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


}
