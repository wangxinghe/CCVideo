package com.mouxuejie.ccvideo;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mouxuejie.ccvideo.ui.activity.MessageActivity;
import com.mouxuejie.ccvideo.ui.activity.ProfileActivity;
import com.mouxuejie.ccvideo.ui.activity.SearchActivity;
import com.mouxuejie.ccvideo.ui.activity.SettingsActivity;
import com.mouxuejie.ccvideo.ui.fragment.FeedFragment;
import com.mouxuejie.ccvideo.ui.activity.CameraActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    ImageView mAvatarIv;
    TextView mNameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.menu_white_24);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.feed_fragment, FeedFragment.newInstance(), FeedFragment.class.getSimpleName())
                .commitAllowingStateLoss();

        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        View navHeaderView = mNavigationView.getHeaderView(0);
        mAvatarIv = navHeaderView.findViewById(R.id.avatar_iv);
        mNameTv = navHeaderView.findViewById(R.id.name_tv);
        mAvatarIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileActivity.jump(MainActivity.this);
            }
        });
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(false);
                mDrawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.nav_message:
                        MessageActivity.jump(MainActivity.this);
                        break;
                    case R.id.nav_search:
                        SearchActivity.jump(MainActivity.this);
                        break;
                    case R.id.nav_settings:
                        SettingsActivity.jump(MainActivity.this);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_camera:
                CameraActivity.jump(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
