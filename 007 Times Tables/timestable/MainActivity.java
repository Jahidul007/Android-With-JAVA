package example.com.timestable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;


    public void generateTimesTable(int timesTable){

        ArrayList<String> timesTableContent  = new ArrayList<>();

        for(int i  = 1; i<=10; i++){

            timesTableContent.add(Integer.toString(i*timesTable));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,timesTableContent);

        listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar timesTaableSeekBar = (SeekBar) findViewById(R.id.tmesTableSeekBar);

        listView = (ListView) findViewById(R.id.listView);

        timesTaableSeekBar.setMax(20);

        timesTaableSeekBar.setProgress(10);

        timesTaableSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int min = 1;

                int timesTable;

                if (progress < min) {

                    timesTable = min;
                    timesTaableSeekBar.setProgress(min);
                } else {
                    timesTable = progress;
                }

                generateTimesTable(timesTable);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

       generateTimesTable(10);

    }
}
