package com.example.proyectointegradorgrupal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

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
import com.example.proyectointegradorgrupal.view.adapter.ViewPagerAdapter;
import com.example.proyectointegradorgrupal.view.fragment.FragmentDetalleCancion;
import com.example.proyectointegradorgrupal.view.fragment.FragmentReproductor;

import org.w3c.dom.Text;

import java.io.IOException;

public class ReproductorActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private Uri uriTrack;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Track track = (Track) bundle.get("track");
        final String preview = track.getPreview();
        uriTrack = Uri.parse(preview);

        FragmentDetalleCancion fragmentDetalleCancion = new FragmentDetalleCancion();
        FragmentReproductor fragmentReproductor = new FragmentReproductor();
        fragmentReproductor.setArguments(bundle);

        viewPager = findViewById(R.id.activityReproductorViewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 2, fragmentReproductor, fragmentDetalleCancion);
        viewPager.setAdapter(viewPagerAdapter);


    }


}
