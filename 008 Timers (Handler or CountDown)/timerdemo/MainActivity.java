package example.com.timerdemo;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(10000, 1000) {

            public  void  onTick(long milliSecondUnitDone){

                // countDown is counting down (Every second )
                Log.i("Seconds left: ",String.valueOf(milliSecondUnitDone/1000));
            }

            public  void onFinish(){

                // counter is finished (After ten seconds)
                Log.i("Done!","CountDown timer finished");

            }
        }.start();

        /*final Handler handler  = new Handler();

        Runnable run = new Runnable() {
            @Override
            public void run() {

                // Insert code to be run every second

                Log.i("run: Runnable has run","a second must have passed");

                handler.postDelayed(this,1000);
            }
        };
        handler.post(run);

    }*/
    }
}
