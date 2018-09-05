package example.com.firebasesavingdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ArtistListActivity extends AppCompatActivity {

    public static final String ARTIST_NAME ="artistname";
    public static final String ARTIST_ID ="artistid";



    ListView listView;
    DatabaseReference databaseReference;

    List<Artist>  artistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_list);

        databaseReference = FirebaseDatabase.getInstance().getReference("artists");

        listView = (ListView) findViewById(R.id.listViewArtists);

        artistList = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Artist artist = artistList.get(position);

                Intent intent = new Intent(getApplicationContext(),AddTrackActivity.class);

                intent.putExtra(ARTIST_ID,artist.getArtistId());
                intent.putExtra(ARTIST_NAME,artist.getArtistName());

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                artistList.clear();

                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){

                    Artist artist = artistSnapshot.getValue(Artist.class);

                    artistList.add(artist);
                }

                ArtistList adapter = new ArtistList(ArtistListActivity.this,artistList);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
