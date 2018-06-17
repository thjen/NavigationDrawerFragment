package qthjen_dev.io.navigationdrawerfragment;


import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mRootLayout;
    private FrameLayout mFlContent;

    private LinearLayout mItemFirst, mItemSecond, mItemThird;
    private TextView mTitle1, mTitle2, mTitle3;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.main);
        mNavigationView = findViewById(R.id.navigation);
        mRootLayout = findViewById(R.id.drawerLayout);
        mFlContent = findViewById(R.id.flContent);
        mItemFirst = findViewById(R.id.nav_first);
        mItemSecond = findViewById(R.id.nav_second);
        mItemThird = findViewById(R.id.nav_third);
        mTitle1 = findViewById(R.id.nav_title1);
        mTitle2 = findViewById(R.id.nav_title2);
        mTitle3 = findViewById(R.id.nav_title3);

        mToolbar.getContentInsetEnd();
        mToolbar.setNavigationIcon(R.drawable.menuu);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRootLayout.openDrawer(GravityCompat.START);
            }
        });

        getSupportActionBar().setTitle("First Fragment");

        mDrawerToggle = setupDrawerToggle();
        mRootLayout.addDrawerListener(mDrawerToggle);
        setUpHomeFragment();
        setUpItemSelected();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void setUpHomeFragment() {
        setCheckedItem(mTitle1, R.color.colorPrimary);
        setCheckedItem(mTitle2, android.R.color.primary_text_light);
        setCheckedItem(mTitle3, android.R.color.primary_text_light);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction().add(R.id.flContent, new FirstFragment()).commit();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mRootLayout, R.string.drawer_open, R.string.drawer_close);
    }

    private void setUpItemSelected() {
        mItemFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDrawerItem(0);
            }
        });
        mItemSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDrawerItem(1);
            }
        });
        mItemThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDrawerItem(2);
            }
        });
    }

    private void setCheckedItem(TextView tv, int color) {
        tv.setTextColor(getResources().getColor(color));
    }

    private void selectDrawerItem(int item) {
        Fragment fragment = null;
        Class fragmentClass;
        switch (item) {
            case 0: fragmentClass = FirstFragment.class;
                mToolbar.setTitle(mTitle1.getText().toString());
                setCheckedItem(mTitle1, R.color.colorPrimary);
                setCheckedItem(mTitle2, android.R.color.primary_text_light);
                setCheckedItem(mTitle3, android.R.color.primary_text_light);
            break;
            case 1: fragmentClass = SecondFragment.class;
                mToolbar.setTitle(mTitle2.getText().toString());
                setCheckedItem(mTitle2, R.color.colorPrimary);
                setCheckedItem(mTitle1, android.R.color.primary_text_light);
                setCheckedItem(mTitle3, android.R.color.primary_text_light);
            break;
            case 2: fragmentClass = ThirdFragment.class;
                mToolbar.setTitle(mTitle3.getText().toString());
                setCheckedItem(mTitle3, R.color.colorPrimary);
                setCheckedItem(mTitle2, android.R.color.primary_text_light);
                setCheckedItem(mTitle1, android.R.color.primary_text_light);
            break;

            default:
                fragmentClass = FirstFragment.class;
                mToolbar.setTitle(mTitle1.getText().toString());
                setCheckedItem(mTitle1, R.color.colorPrimary);
                setCheckedItem(mTitle2, android.R.color.primary_text_light);
                setCheckedItem(mTitle3, android.R.color.primary_text_light);
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        mRootLayout.closeDrawers();
    }
}
