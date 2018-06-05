package example.com.whatstheweather;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    EditText city;
    TextView ResultTextView;

    public void findWeather(View view) {

        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(city.getWindowToken(), 0);

        try {
            String encodedCityName = URLEncoder.encode(city.getText().toString(), "UTF-8");

            Downloadtask task = new Downloadtask();

            task.execute("http://api.apixu.com/v1/forecast.json?key=29f5ab652fd1476983275340180306&q="+encodedCityName+"&days=5" );

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();

            ResultTextView.setText("Invalid Location!");
            //Toast.makeText(getApplicationContext(), "Could not find weather!", Toast.LENGTH_LONG).show();
        }


    }

    public class Downloadtask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";

            URL url;

            HttpURLConnection httpURLConnection = null;


            try {

                url = new URL(urls[0]);


                httpURLConnection = (HttpURLConnection) url.openConnection();


                InputStream inputStream = httpURLConnection.getInputStream();


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);


                int data = inputStreamReader.read();


                while (data != -1) {


                    char current = (char) data;


                    result += current;


                    data = inputStreamReader.read();

                }
                return result;


            } catch (Exception e) {

                e.printStackTrace();

                return "failed";

            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {

                String message = "";
                String cityName = city.getText().toString().toUpperCase();

                // JSONString parse

                JSONObject weatherInfo = (new JSONObject(result)).getJSONObject("current");

                String temp = weatherInfo.getString("temp_c");

                String temperature = "";

                // Log.i("Temp","Temperature: "+temp);

                if (temp != "") {

                    message += cityName + "\n" + temp + "℃";

                } else {

                    ResultTextView.setText("Please Enter a Location");
                    // Toast.makeText(getApplicationContext(), "Could not find weather!", Toast.LENGTH_LONG).show();

                }
                if (message != "") {

                    ResultTextView.setText(message);

                } else {

                    ResultTextView.setText("Please Enter a Location");

                }

                /* // JSONArray
                JSONObject jsonObject = new JSONObject(result);
                String weatherInfo1 = jsonObject.getString("weather");
                JSONArray jsonArray = new JSONArray(weatherInfo1);

                for(int i = 0 ;i<jsonArray.length(); i++){

                    JSONObject jsonPart = jsonArray.getJSONObject(i);
                    Log.i("main",jsonPart.getString("main"));
                    Log.i("descripsion",jsonPart.getString("description"));
                }*/



            } catch (JSONException e) {

                e.printStackTrace();
                ResultTextView.setText("");
                Toast.makeText(getApplicationContext(), "Could not find weather!", Toast.LENGTH_LONG).show();

            }


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = (EditText) findViewById(R.id.city);
        ResultTextView = (TextView) findViewById(R.id.ResultTextView);


    }
}
