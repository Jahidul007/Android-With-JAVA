package com.jahid.recyclerview.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jahid.recyclerview.R;

public class DetailsActivity extends AppCompatActivity {

    private TextView title, description, rating;

    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        title = (TextView) findViewById(R.id.titleId);
        description = (TextView) findViewById(R.id.descriptionId);
        rating = (TextView) findViewById(R.id.ratingId);

        extras = getIntent().getExtras();

        if(extras!=null){
            title.setText(extras.getString("title"));
            description.setText(extras.getString("description"));
            rating.setText(extras.getString("rating"));
        }

    }
}
