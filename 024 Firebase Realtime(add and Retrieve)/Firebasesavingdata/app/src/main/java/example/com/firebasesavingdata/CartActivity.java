package example.com.firebasesavingdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    ListView listView;
    Button yes,no;

    DatabaseReference mCart;

    List<Cart> cartList;
    LinearLayout linearLayout;

    public static final String TRACK_NAME= "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        listView = (ListView) findViewById(R.id.cartList);
        linearLayout = (LinearLayout) findViewById(R.id.dialogBox);
        yes = (Button) findViewById(R.id.buttonYes);
        no = (Button) findViewById(R.id.buttonNo);

        mCart = FirebaseDatabase.getInstance().getReference("cartList");

        cartList = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final Cart artist = cartList.get(position);

                linearLayout.setVisibility(View.VISIBLE);

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        Intent intent = new Intent(getApplicationContext(),Order.class);

                        intent.putExtra(TRACK_NAME,artist.getName());

                        startActivity(intent);

                        linearLayout.setVisibility(View.INVISIBLE);

                        finish();
                    }
                });



                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        linearLayout.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        mCart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                cartList.clear();

                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){

                    Cart artist = artistSnapshot.getValue(Cart.class);

                    cartList.add(artist);
                }

                CartList adapter = new CartList(CartActivity.this,cartList);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}