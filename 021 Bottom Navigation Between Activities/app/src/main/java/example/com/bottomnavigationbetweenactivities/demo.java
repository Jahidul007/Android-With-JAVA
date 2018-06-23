package example.com.bottomnavigationbetweenactivities;

public class demo extends BaseActivity {
    @Override
    int getContentViewId() {
        return R.layout.demo;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.news;
    }
}
