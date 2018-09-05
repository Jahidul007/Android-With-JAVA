package example.com.firebasesavingdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddTrackActivity extends AppCompatActivity {

    TextView textViewName;
    EditText editTextTrackName;
    SeekBar seekBarRating;
    ListView listViewTrackList;

    Button button,buttonTrack;

    DatabaseReference databaseTracks;

    List<Track> tracks;

    public static final String TRACK_NAME = "trackName";
    public static final String TRACK_ID = "trackId";
    public static final String TRACK_ALBUM = "trackAlbum";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_track);

        textViewName = (TextView) findViewById(R.id.textViewArtistName);
        editTextTrackName = (EditText) findViewById(R.id.editTextTrackName);
        seekBarRating = (SeekBar) findViewById(R.id.seekBar);
        listViewTrackList = (ListView) findViewById(R.id.trackListView);
        button = (Button) findViewById(R.id.buttonAddTrack);

       // buttonTrack = (Button) findViewById(R.id.buttonTrackList);
        Intent intent = getIntent();

        System.out.println("Intent"+intent);

        tracks = new ArrayList<>();




        String id = intent.getStringExtra(ArtistListActivity.ARTIST_ID);
        String name = intent.getStringExtra(ArtistListActivity.ARTIST_NAME);

        System.out.println("add id = "+ id );

        textViewName.setText(name);

        databaseTracks = FirebaseDatabase.getInstance().getReference("tracks").child(id);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTrack();
            }
        });

        listViewTrackList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Track artist = tracks.get(position);

                Intent intent = new Intent(getApplicationContext(),Album.class);

                intent.putExtra(TRACK_ID,artist.getTrackId());
                intent.putExtra(TRACK_NAME,artist.getTrackName());

                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseTracks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                tracks.clear();
                for(DataSnapshot trackSnapshot : dataSnapshot.getChildren()){

                    Track track = trackSnapshot.getValue(Track.class);
                    System.out.println(track);

                    tracks.add(track);
                }

                TrackList adapter = new TrackList(AddTrackActivity.this,tracks);
                listViewTrackList.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    private void saveTrack() {

        String trackName = editTextTrackName.getText().toString().trim();
        int rating = seekBarRating.getProgress();

        if(!TextUtils.isEmpty(trackName)){

            String id = databaseTracks.push().getKey();

            Track track = new Track(id,trackName,rating);
            databaseTracks.child(id).setValue(track);
            Toast.makeText(this,"Track save successfully",Toast.LENGTH_LONG).show();

        } else{
            Toast.makeText(this,"Track name should not be empty",Toast.LENGTH_LONG).show();
        }
    }
}
