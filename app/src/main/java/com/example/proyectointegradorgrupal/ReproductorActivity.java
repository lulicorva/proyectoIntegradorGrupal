package com.example.proyectointegradorgrupal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.service.OnClearFromNotificationService;
import com.example.proyectointegradorgrupal.view.CreateNotification;
import com.example.proyectointegradorgrupal.view.MainActivity;
import com.example.proyectointegradorgrupal.view.ReproductorSingleton;
import com.example.proyectointegradorgrupal.view.adapter.ViewPagerAdapterReproductor;
import com.example.proyectointegradorgrupal.view.fragment.FragmentReproductorSingleton;

public class ReproductorActivity extends AppCompatActivity implements FragmentReproductorSingleton.FragmentReproductorSingletonListener {

    private ViewPager viewPager;
    private ViewPagerAdapterReproductor viewPagerAdapterReproductor;

    public int posicionAlScrollear;
    public Track trackList;
    public ReproductorSingleton reproductorSingleton;

    private NotificationManager notificationManager;
    private CreateNotification createNotification;



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
        reproductorSingleton = ReproductorSingleton.getInstance();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                posicionAlScrollear = position;

                reproductorSingleton.getMediaPlayer().pause();

                String preview = trackList.getData().get(position).getPreview();
                Uri uriTrack = Uri.parse(preview);

                reproductorSingleton.setNewMediaPlayer();
                reproductorSingleton.prepareMediaPlayer(ReproductorActivity.this, uriTrack);
                reproductorSingleton.getMediaPlayer().start();

                FragmentReproductorSingleton fragmentReproductorSingleton = (FragmentReproductorSingleton) viewPagerAdapterReproductor.getItem(position);
                fragmentReproductorSingleton.updateSeekBar();
                fragmentReproductorSingleton.setPlayPause();




                createNotification = CreateNotification.getInstance();
                createNotification.createNotificacion(ReproductorActivity.this,
                        trackList.getData().get(position),
                        R.drawable.ic_pause_circle_filled, 1,
                        trackList.getData().size());

            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

/**
 * Notificacion usa metodo createChannel
 * */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
            registerReceiver(broadcastReceiver, new IntentFilter("TRACKS_TRACKS"));
            startService(new Intent(ReproductorActivity.this, OnClearFromNotificationService.class));

        }


    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CreateNotification.CHANNEL_ID, "Jaxoo", NotificationManager.IMPORTANCE_LOW);
            notificationManager = getSystemService(NotificationManager.class);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }

        }

    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getExtras().getString("actionName");

            switch (action) {

                case CreateNotification.NEXT_TRACK:
                    onClickNext();
                    break;
                case CreateNotification.PREVIOUS_TRACK:
                    onClickPrevious();
                    break;

                case CreateNotification.PLAYPAUSE:

                    if (reproductorSingleton.getMediaPlayer().isPlaying()) {
                        reproductorSingleton.getMediaPlayer().pause();


                        createNotification = CreateNotification.getInstance();
                        createNotification.createNotificacion(ReproductorActivity.this,
                                trackList.getData().get(posicionAlScrollear),
                                R.drawable.ic_play_circle_filled, 1,
                                trackList.getData().size());



                    } else {
                        reproductorSingleton.getMediaPlayer().start();


                        createNotification = CreateNotification.getInstance();
                        createNotification.createNotificacion(ReproductorActivity.this,
                                trackList.getData().get(posicionAlScrollear),
                                R.drawable.ic_pause_circle_filled, 1,
                                trackList.getData().size());

                    }
                    break;


            }


        }
    };


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

    @Override
    protected void onDestroy() {
        super.onDestroy();
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationManager.cancelAll();
        }
        unregisterReceiver(broadcastReceiver);

*/

    }

    @Override
    public void onBackPressed() {

        Intent data = new Intent();



        Bundle bundle = new Bundle();
        bundle.putSerializable("trackList", trackList);
        bundle.putInt("position", posicionAlScrollear);
        data.putExtras(bundle);


        setResult(10, data);

        super.onBackPressed();

    }





}
