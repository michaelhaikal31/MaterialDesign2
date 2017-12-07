package com.example.haikal.materialdesign2.Notifications;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.haikal.materialdesign2.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Date;

public class NotoficationsActivity extends AppCompatActivity {
    private Button NotifText, NotifImages, NotifLayout;
    private NotificationCompat.Builder builder;
    private Bitmap profilImageBitmap;
    private Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notofications);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeButtonEnabled(true);

        this.getSupportActionBar().setTitle(NotificationCompat.class.getSimpleName());
        TextView date = (TextView)findViewById(R.id.date);
        java.text.DateFormat timeFormat = DateFormat.getTimeFormat(getBaseContext());
        java.text.DateFormat datelong = DateFormat.getMediumDateFormat(getBaseContext());
        date.setText(timeFormat.format(new Date())+","+datelong.format(new Date()));

        //make bitmap image for notif
        profilImageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profileimage);

        NotifText = (Button) findViewById(R.id.notifText);
        NotifText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make Builder for notification
                builder = new NotificationCompat.Builder(getBaseContext());

                notification = builder
                        .setContentText("This is Notification")
                        .setContentTitle("Title")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setColor(Color.parseColor("#4b8a08"))
                        .build();
                NotificationManager(notification);
            }
        });

        NotifImages = (Button) findViewById(R.id.notifImage);
        NotifImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make Builder for notification
                builder = new NotificationCompat.Builder(getBaseContext());

                notification = builder.setContentTitle("Title")
                        .setContentText("This is Notification")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(profilImageBitmap)//Bitmap
                        .setColor(Color.parseColor("#4b8a08"))
                        .build();
                NotificationManager(notification);
            }
        });

        NotifLayout = (Button) findViewById(R.id.notifLayout);
        NotifLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder = new NotificationCompat.Builder(getBaseContext());
                NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle(builder);
                style.bigPicture(profilImageBitmap)
                        .bigLargeIcon(profilImageBitmap)
                        .setBigContentTitle("Expended Title")
                        .setSummaryText("Summary text");
                notification = builder.setContentTitle("Title")
                        .setContentText("This is Notification")
                        .setSmallIcon(R.drawable.profileimage)
                        .setColor(Color.parseColor("#4b8a08"))
                        .setStyle(style)
                        .build();
                NotificationManager(notification);
            }
        });

        Admod();

    }

    public void NotificationManager(Notification notification) {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getBaseContext());
        notificationManagerCompat.notify(0x1234, notification);
    }


    private void Admod() {
        //admod
        AdView adView = (AdView)findViewById(R.id.admod_notif);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }
}
