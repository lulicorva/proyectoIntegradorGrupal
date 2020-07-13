package com.example.proyectointegradorgrupal.view;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.ReproductorActivity;
import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.service.NotificationActionService;

public class CreateNotification {

    public static final String CHANNEL_ID = "channelId";
    public static final String PLAYPAUSE = "playPause";
    public static final String PREVIOUS_TRACK = "previousTrack";
    public static final String NEXT_TRACK = "nextTrack";

    public static Notification notification;

    private static CreateNotification INSTANCE = null;


    private synchronized static void createInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CreateNotification();
        }
    }

    public static CreateNotification getInstance() {
        if (INSTANCE == null) {
            createInstance();
            notification = new Notification();
        }
        return INSTANCE;
    }


    public static void createNotificacion(Context context, Track track, int playButtonID, int position, int size) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context, "tag");

            //el id deberia ser track.getCover() (deberia ser un int)
            Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.jaxoologin);


            PendingIntent pendingIntentPrevious;
            int drw_previous;
            if (position == 0) {
                pendingIntentPrevious = null;
                drw_previous = 0;

            } else {

                Intent intentPrevious = new Intent(context, NotificationActionService.class)
                        .setAction(PREVIOUS_TRACK);

                pendingIntentPrevious = PendingIntent.getBroadcast(context, 0, intentPrevious, PendingIntent.FLAG_UPDATE_CURRENT);
                drw_previous = R.drawable.ic_previous_24;

            }

            Intent intentPlayPause = new Intent(context, NotificationActionService.class)
                    .setAction(PLAYPAUSE);

            PendingIntent pendingIntentPlayPause = PendingIntent.getBroadcast(context, 0, intentPlayPause, PendingIntent.FLAG_UPDATE_CURRENT);


            PendingIntent pendingIntentNext;
            int drw_next;
            if (position == size) {
                pendingIntentNext = null;
                drw_next = 0;

            } else {

                Intent intentNext = new Intent(context, NotificationActionService.class)
                        .setAction(NEXT_TRACK);

                pendingIntentNext = PendingIntent.getBroadcast(context, 0, intentNext, PendingIntent.FLAG_UPDATE_CURRENT);
                drw_next = R.drawable.ic_next_24;

            }


            //prueba para que al hacer click sobre la noti me abra el reproductor
           /* Intent resultIntent = new Intent(context, ReproductorActivity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addNextIntentWithParentStack(resultIntent);

            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
*/


            //creo la notificacion
            notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_music_note_24)
                    .setContentTitle(track.getTitle())
                    .setContentText(track.getArtist().getName())
                    .setLargeIcon(icon)
                    .setOnlyAlertOnce(true) //muestra notif solo la primera vez
                    .setShowWhen(false)
                    .addAction(drw_previous, "Previous", pendingIntentPrevious)
                    .addAction(playButtonID, "Play", pendingIntentPlayPause)
                    .addAction(drw_next, "Next", pendingIntentNext)

                  //  .setContentIntent(resultPendingIntent) //prueba reproductor activity

                    .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                            .setShowActionsInCompactView(0, 1, 2)
                            .setMediaSession(mediaSessionCompat.getSessionToken()))
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .build();

            notificationManagerCompat.notify(1, notification);


        }

    }
}
