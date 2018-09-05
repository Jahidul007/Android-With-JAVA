package example.com.firebasesavingdata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Order extends AppCompatActivity{

    Button confirm,back;
    EditText user,email,phone,address;
    TextView textView;

    DatabaseReference mOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_order_layout);

        confirm = (Button) findViewById(R.id.buttonConfirm);
        back = (Button) findViewById(R.id.buttonBack);

        user = (EditText) findViewById(R.id.editTextUserName);
        email = (EditText) findViewById(R.id.editTextEmail);
        phone = (EditText) findViewById(R.id.editTextPhone);
        address = (EditText) findViewById(R.id.editTextAddress);
        textView =(TextView) findViewById(R.id.textView);

        Intent intent = getIntent();
        String name = intent.getStringExtra(CartActivity.TRACK_NAME);

        System.out.println("ProductName: "+name);

        textView.setText(name);

        mOrder = FirebaseDatabase.getInstance().getReference("orderList");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmOrder();

                Intent intent = new Intent(getApplicationContext(),CartActivity.class);
                startActivity(intent);

                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CartActivity.class);

                startActivity(intent);
                finish();
            }
        });
    }

    private void confirmOrder() {
        Intent intent = getIntent();
        String productName = intent.getStringExtra(CartActivity.TRACK_NAME);

        System.out.println("ProductName: "+productName);

        String userName = user.getText().toString().trim();
        String emailad = email.getText().toString().trim();
        String phoneN = phone.getText().toString().trim();
        String addressN = address.getText().toString().trim();

        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(emailad) &&
                !TextUtils.isEmpty(phoneN) && !TextUtils.isEmpty(addressN)) {

            String id = mOrder.push().getKey();

            OrderMaterials orderMaterials = new OrderMaterials(id, userName, emailad,phoneN,addressN,productName);

            mOrder.child(id).setValue(orderMaterials);

            Toast.makeText(this, "Added to orderlist", Toast.LENGTH_LONG).show();


        } else {
            Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();
        }

    }
}
