package example.com.slidingtablayout;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

public class MainActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapterViewPager = new SampleFragmentPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapterViewPager);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
        private int NUM_ITEMS = 4;


        public SampleFragmentPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
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
                    return FragmentWithOneImage.newInstance("Temperature graph", R.drawable.images);
                case 1:
                    return FragmentWithOneImage.newInstance("Pressure graph", R.drawable.images1);
                case 2:
                    return FragmentWithOneImage.newInstance("Wind graph", R.drawable.red);
                case 3:
                    return FragmentWithOneImage.newInstance("Humidity graph", R.drawable.images1);
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {

            String tabName[] ={"Temp","Precip","Wind","Humidity"};
            return tabName[position];
        }

    }
}
