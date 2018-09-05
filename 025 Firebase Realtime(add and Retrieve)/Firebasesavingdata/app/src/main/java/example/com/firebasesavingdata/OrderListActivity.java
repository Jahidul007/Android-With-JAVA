package example.com.firebasesavingdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderListActivity extends AppCompatActivity {

    ListView listView;
    List<OrderMaterials> orderDetails;

    DatabaseReference mOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        listView = (ListView) findViewById(R.id.listViewOrder);

        mOrder = FirebaseDatabase.getInstance().getReference("orderList");

        orderDetails = new ArrayList<>();

    }
    @Override
    protected void onStart() {
        super.onStart();

        mOrder.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                orderDetails.clear();

                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){

                    OrderMaterials artist = artistSnapshot.getValue(OrderMaterials.class);

                    orderDetails.add(artist);
                }

                OrderList adapter = new OrderList(OrderListActivity.this,orderDetails);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
