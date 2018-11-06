package com.jahid.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class ExampleBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "EBR triggered",Toast.LENGTH_SHORT).show();

        /*   if ("com.broadcast.EXAMPLE_ACTION".equals(intent.getAction())) {
         boolean noConnectivity = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
            );

            if (noConnectivity) {

                Toast.makeText(context, "Disconnected", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Connected", Toast.LENGTH_LONG).show();
            }

        }

        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {

            Toast.makeText(context, "Connectivity Changed", Toast.LENGTH_LONG).show();

        }

        }*/
    }
}
