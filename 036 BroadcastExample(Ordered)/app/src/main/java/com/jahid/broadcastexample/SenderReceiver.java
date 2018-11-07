package com.jahid.broadcastexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class SenderReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int resultCode = getResultCode();
        String resultData = getResultData();
        Bundle resultExtra = getResultExtras(true);
        String extra = resultExtra.getString("stringExtra");

        resultCode++;
        extra +="->senderReceiver";

        String toastText = "senderReceiver\n" +
                "result code: "+ resultCode + "\n"+
                "result data: "+ resultData + "\n"+
                "Extra: " +extra;

        Toast.makeText(context, toastText,Toast.LENGTH_LONG).show();

        resultData = "senderReceiver";
        resultExtra.putString("stringExtra",extra);

        setResult(resultCode,resultData,resultExtra);
    }
}
