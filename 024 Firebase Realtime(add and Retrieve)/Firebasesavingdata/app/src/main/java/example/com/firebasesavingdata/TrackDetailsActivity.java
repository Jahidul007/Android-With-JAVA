package example.com.firebasesavingdata;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class TrackDetailsActivity extends AppCompatActivity {

    EditText album,releaseYear;
    Button button;


    ListView listView;
    List<TrackDetails> albums;
    DatabaseReference databaseTracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_details);

        album = ( EditText) findViewById(R.id.editTextAlbumName);
        releaseYear= (EditText) findViewById(R.id.editTextReleaseYear);
        button = (Button) findViewById(R.id.buttonAddAlbum);

        listView = (ListView) findViewById(R.id.albumDetailListView);


        Intent intent = getIntent();

        System.out.println("Intent"+intent);

        albums = new ArrayList<>();


        String id = intent.getStringExtra(AddTrackActivity.TRACK_ID);
        String name = intent.getStringExtra(AddTrackActivity.TRACK_NAME);

        databaseTracks = FirebaseDatabase.getInstance().getReference("albums").child(id);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAlbum();
            }
        });
    }

    private void saveAlbum() {
        String albumname = album.getText().toString().trim();
        int year = Integer.parseInt(releaseYear.getText().toString());

        if(!TextUtils.isEmpty(albumname)){

            String id = databaseTracks.push().getKey();

            TrackDetails track = new TrackDetails(id,albumname,year);
            databaseTracks.child(id).setValue(track);
            Toast.makeText(this,"Album save successfully",Toast.LENGTH_LONG).show();

        } else{
            Toast.makeText(this,"Album name should not be empty",Toast.LENGTH_LONG).show();
        }

    }
}
