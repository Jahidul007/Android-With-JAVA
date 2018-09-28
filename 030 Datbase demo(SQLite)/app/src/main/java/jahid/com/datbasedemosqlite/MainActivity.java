package jahid.com.datbasedemosqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users(name VARCHAR, age INT(3))");

            /**insert**/
            //myDatabase.execSQL("INSERT INTO users(name,age) VALUES('Rob',34)");
            //myDatabase.execSQL("INSERT INTO users(name,age) VALUES('Don',23)");

            /**delete**/
            //myDatabase.execSQL("DELETE FROM users where name = 'Rob'");

            /**update**/
            myDatabase.execSQL("UPDATE users SET age = 30 where name ='Don'");

            Cursor c = myDatabase.rawQuery("SELECT * FROM users LIMIT 2", null);

            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");


            if (c != null && c.moveToFirst()) {

                do {
                    Log.i("name", c.getString(nameIndex));
                    Log.i("age", Integer.toString(c.getInt(ageIndex)));

                } while (c.moveToNext());

            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
