package com.jahid.notificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final int MY_NOTIFICATION_ID= 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), MY_NOTIFICATION_ID,intent,0);

        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle("Lunch is ready")
                .setContentText("It's getting cool...........")
                .setSmallIcon(android.R.drawable.ic_menu_call)
                .setContentIntent(pendingIntent)
                .addAction(android.R.drawable.sym_action_chat,"chat", pendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(MY_NOTIFICATION_ID,notification);

    }
}
