package example.com.jsondemo;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {


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


                // JSONString parse
                JSONObject weatherInfo = (new JSONObject(result)).getJSONObject("main");
                String temp = weatherInfo.getString("temp");
                Log.i("Temp","Pressure: "+weatherInfo.getString("pressure"));
                Log.i("Temp","Temperature: "+temp);

                // JSONArray
                JSONObject jsonObject = new JSONObject(result);
                String weatherInfo1 = jsonObject.getString("weather");
                JSONArray jsonArray = new JSONArray(weatherInfo1);

                for(int i = 0 ;i<jsonArray.length(); i++){

                    JSONObject jsonPart = jsonArray.getJSONObject(i);
                    Log.i("main",jsonPart.getString("main"));
                    Log.i("descripsion",jsonPart.getString("description"));
                }



            } catch (JSONException e){
                e.printStackTrace();
            }



        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Downloadtask task = new Downloadtask();
        task.execute("http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");



    }
}
