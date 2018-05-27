package example.com.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void fade( View view){

        ImageView  imageView = (ImageView) findViewById(R.id.imageView);

        imageView.animate()
                .translationXBy(1000f)
                .translationYBy(1000f)
                .rotation(3600)
                .setDuration(3000);

        //imageView.animate().scaleX(0.5f).scaleY(.05f).setDuration(3000);

        //imageView.animate().scaleXBy(0.5f).scaleYBy(.05f).setDuration(3000);

        //imageView.animate().rotation(1800f).setDuration(3000);

       /* ImageView  imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView2.animate().alpha(1f).setDuration(2000);*/

         //imageView.setImageResource(R.drawable.images);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView  imageView = (ImageView) findViewById(R.id.imageView);

        imageView.setTranslationX(-1000f);
        imageView.setTranslationY(-1000f);
    }
}
