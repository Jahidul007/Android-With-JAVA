package example.com.firebasesavingdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TrackListActivity extends AppCompatActivity {



    ListView listView;
    DatabaseReference databaseTracks;
    ListView listViewTrackList;

    List<Track> tracks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_list);

        Intent intent = getIntent();
        String id = intent.getStringExtra(AddTrackActivity.TRACK_ID);
        String name = intent.getStringExtra(AddTrackActivity.TRACK_NAME);

        System.out.println("track id = "+ id );


        Track track = new Track();
        System.out.println("track id = "+ track.getTrackId());

        databaseTracks = FirebaseDatabase.getInstance().getReference("tracks").child(id);
        listView = (ListView) findViewById(R.id.listViewArtists);

        tracks = new ArrayList<>();


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

                    tracks.add(track);
                }

                TrackList adapter = new TrackList(TrackListActivity.this,tracks);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
   /** @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                trackList.clear();

                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {

                    Track track = artistSnapshot.getValue(Track.class);

                    trackList.add(track);
                }

                TrackList adapter = new TrackList(TrackListActivity.this, trackList);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
**/


}
