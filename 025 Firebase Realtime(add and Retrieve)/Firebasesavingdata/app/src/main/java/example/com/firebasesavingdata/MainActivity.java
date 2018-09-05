package example.com.firebasesavingdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button,buttonArtist,buttonCart,buttonOrder;
    Spinner spinner;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference("artists");

        editText = (EditText) findViewById(R.id.editText);
        spinner = (Spinner) findViewById(R.id.spinner);
        button = (Button) findViewById(R.id.addbutton);
        buttonArtist = (Button) findViewById(R.id.buttonArtistList);

        buttonCart = (Button) findViewById(R.id.cartList);
        buttonOrder = (Button) findViewById(R.id.orderList);


        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,OrderListActivity.class);
                startActivity(intent);

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addArtist();
                editText.clearFocus();
                editText.setText("");

            }
        });
        buttonArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,ArtistListActivity.class);
                startActivity(intent);

            }
        });
        buttonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,CartActivity.class);
                startActivity(intent);

            }
        });
    }

    private void addArtist() {

        String name = editText.getText().toString().trim();
        String genre = spinner.getSelectedItem().toString();

        if (!TextUtils.isEmpty(name)) {

            String id = databaseReference.push().getKey();

            Artist artist = new Artist(id, name, genre);

            databaseReference.child(id).setValue(artist);

            Toast.makeText(this, "Artist added", Toast.LENGTH_LONG).show();


        } else {
            Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();
        }
    }
}
