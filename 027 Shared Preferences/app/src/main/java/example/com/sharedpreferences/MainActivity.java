package example.com.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // System.out.println("jahid");

        SharedPreferences sharedPreferences = this.getSharedPreferences("example.com.sharedpreferences", Context.MODE_PRIVATE);

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Don");
        arrayList.add("Donkey");
        arrayList.add("Bodka");
        arrayList.add("Pok pok");

        try {
            sharedPreferences.edit().putString("arrayList", ObjectSerializer.serialize(arrayList)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> newArrayList = new ArrayList<>();

        try {
            newArrayList = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("arrayList",
                    ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("ArrayList ", newArrayList.toString());
        // sharedPreferences.edit().putString("username","Jahid").apply();
        // String username  = sharedPreferences.getString("username","");

        // System.out.println("Username: " + username);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {

            case R.id.settings:
                Log.i("Menu Item selected", "Settings");

                return true;

            case R.id.help:
                Log.i("Menu Item selected", "Help");
                return true;

            default:
                return false;
        }
    }
}
