package example.com.firebasesavingdata;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Album extends AppCompatActivity {

    TextView tname, year, album, rating;
    DatabaseReference databaseReference,mDatabaseReference,mCart;

    ListView listView;

    Button button;

    //FloatingActionButton floatingActionButton;

    List<TrackDetails> albumlist;
    public static final String TRACK_ALBUM = "trackAlbum";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_view);

        tname = (TextView) findViewById(R.id.trackName);

        listView = (ListView) findViewById(R.id.albumList);

        button =(Button) findViewById(R.id.addCart);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });

       // floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton4);


        albumlist = new ArrayList<>();

        Intent intent = getIntent();

        String id = intent.getStringExtra(AddTrackActivity.TRACK_ID);
        String name = intent.getStringExtra(AddTrackActivity.TRACK_NAME);
        final String album_name = intent.getStringExtra(AddTrackActivity.TRACK_ALBUM);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference = mDatabaseReference.child("albums").child(id);
        System.out.println("databases : " + databaseReference);

        tname.setText(name);

    }

    private void addToCart() {
        mCart = mDatabaseReference.child("cartList");
        Intent intent = getIntent();
        String name = intent.getStringExtra(AddTrackActivity.TRACK_NAME);

        if(!TextUtils.isEmpty(name)){

            String id = mCart.push().getKey();

            Cart cart = new Cart(id,name);
            mCart.child(id).setValue(cart);
            Toast.makeText(this,"Add To cart List",Toast.LENGTH_LONG).show();

        } else{
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
        }
    }

    

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                albumlist.clear();

                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {

                    TrackDetails artist = artistSnapshot.getValue(TrackDetails.class);
                    System.out.println(artist);
                    albumlist.add(artist);
                }

                TrackDetailsList adapter = new TrackDetailsList(Album.this, albumlist);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}