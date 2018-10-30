package com.jahid.fragmentdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listViewId);

        String[] name = {"jahid","saifur","fahim","sabbir","Anik","Raihan","Amin"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,name);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

        //forceCrash();


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Fragment fragment;

        if( position == 0){

            fragment = new FragmentDemo();

            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragmentId, fragment);
            transaction.commit();

        } else if (position == 1) {

            fragment = new Fragment2();

            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragmentId, fragment);
            transaction.commit();

        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    public void forceCrash() {
        throw new RuntimeException("This is a force crash");
    }
}
