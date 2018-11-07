package com.jahid.broadcastexample;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textViewID);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast(v);
            }
        });
    }

    private void sendBroadcast(View v) {

        Intent intent = new Intent("com.jahid.EXAMPLE_ACTION");
        intent.setPackage("com.jahid.broadcastdemo");

        Bundle bundle = new Bundle();
        bundle.putString("stringExtra","Start");

        sendOrderedBroadcast(intent, null, new SenderReceiver(),
                null, 0,"start",bundle);

    /* for explicit broadcasting

        Intent intent = new Intent("com.broadcast.EXAMPLE_ACTION");

        intent.putExtra("com.broadcast.EXTRA_TEXT", "Broadcast Received");

        sendBroadcast(intent);


        //intent.setClass(this, SenderReceiver.class);

        ComponentName cn = new ComponentName("com.jahid.broadcastdemo",
                "com.jahid.broadcastdemo.ExampleBroadcastReceiver");
        intent.setComponent(cn);

        intent.setClassName("com.jahid.broadcastdemo",
                "com.jahid.broadcastdemo.ExampleBroadcastReceiver");

        PackageManager packageManager = getPackageManager();

        List<ResolveInfo> infos = packageManager.queryBroadcastReceivers(intent, 0);

        for (ResolveInfo info : infos) {
            ComponentName componentName = new ComponentName(info.activityInfo.packageName,
                    info.activityInfo.name);
            intent.setComponent(componentName);
            sendBroadcast(intent);
        }*/


    /* private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String receivedText = intent.getStringExtra("com.broadcast.EXTRA_TEXT");
            textView.setText(receivedText);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter("com.broadcast.EXAMPLE_ACTION");
        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }*/
    }
}
