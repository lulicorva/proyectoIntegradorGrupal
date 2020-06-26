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
import com.example.proyectointegradorgrupal.view.ReproductorSingleton;
import com.example.proyectointegradorgrupal.view.adapter.ViewPagerAdapter;
import com.example.proyectointegradorgrupal.view.adapter.ViewPagerAdapterPrueba;
import com.example.proyectointegradorgrupal.view.fragment.FragmentDetalleCancion;
import com.example.proyectointegradorgrupal.view.fragment.FragmentReproductor;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.io.IOException;

public class ReproductorActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapterPrueba viewPagerAdapter;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Track trackList = (Track) bundle.get("trackList");


        viewPager = findViewById(R.id.activityReproductorViewPager);
        viewPagerAdapter = new ViewPagerAdapterPrueba(getSupportFragmentManager(), 2, trackList.getData());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

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
