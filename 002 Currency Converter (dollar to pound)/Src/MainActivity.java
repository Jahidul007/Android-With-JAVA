package example.com.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public  void converter(View view){

        EditText dollarEditText = (EditText) findViewById(R.id.dollarEditText);

        Double dollarAmount = Double.parseDouble(dollarEditText.getText().toString());

        Double poundAmount = dollarAmount * 0.75;

        Toast.makeText(MainActivity.this,"£" + String.format("%.2f",poundAmount),Toast.LENGTH_LONG).show();

        Log.i("Amount",dollarEditText.getText().toString());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
