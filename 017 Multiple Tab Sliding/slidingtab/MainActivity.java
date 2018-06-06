package example.com.slidingtab;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;
    PagerTabStrip pagerTabStrip;
    private boolean distributeEvenly;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPager vPager = (ViewPager) findViewById(R.id.vPager);
        pagerTabStrip = (PagerTabStrip) findViewById(R.id.pageHeader);

        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vPager.setAdapter(adapterViewPager);
        pagerTabStrip.setGravity(Gravity.LEFT);






    }
    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 4;


        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

        }

        // Returns total number of pages.
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for a particular page.
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return FragmentWithOneImage.newInstance("Fragment 1", R.drawable.images);
                case 1:
                    return FragmentWithOneImage.newInstance("Fragment 2", R.drawable.images1);
                case 2:
                    return FragmentWithTwoImages.newInstance("Fragment 3", R.drawable.red, R.drawable.images1);
                case 3:
                    return FragmentWithTwoImages.newInstance("Fragment 4", R.drawable.images, R.drawable.images1);
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Tab " + position;
        }


    }

}
