package example.com.bottomnavigationbetweenactivities;

import android.view.View;
import android.widget.Toast;

public class DashboardActivity extends BaseActivity  {


    @Override
    int getContentViewId() {
        return R.layout.activity_dashboard;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_dashboard;
    }
   public void click(View view){
       Toast.makeText(getApplicationContext(), "It's work", Toast.LENGTH_LONG).show();


   }

}
