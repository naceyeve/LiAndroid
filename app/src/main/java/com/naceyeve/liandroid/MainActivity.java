package com.naceyeve.liandroid;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.naceyeve.liandroid.base.ActivityManager;
import com.naceyeve.liandroid.base.AppActivity;
import com.naceyeve.liandroid.base.BaseFragment;
import com.naceyeve.liandroid.home.GirlsFragment;
import com.naceyeve.liandroid.util.ToastUtil;
import com.naceyeve.liandroid.zhihu.ZhihuFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.naceyeve.liandroid.R.id.fab;

public class MainActivity extends AppActivity
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {


    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.nav_view)
    NavigationView mNavView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.content_main)
    FrameLayout mContentMain;
    @Bind(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigation;

    private long exitTime = 0;
    MenuItem currentMenuItme;
    private BaseFragment mFragment;

    @Override
    protected BaseFragment getFirstFragment() {

        mFragment = ZhihuFragment.getInstance();

        return mFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setSupportActionBar(mToolbar);

        mFab = (FloatingActionButton) findViewById(fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavView = (NavigationView) findViewById(R.id.nav_view);
        mNavView.setNavigationItemSelectedListener(this);
        mBottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.content_main;
    }

//    @Override
//    public void onBackPressed() {
//
//        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
//            mDrawerLayout.closeDrawer(GravityCompat.START);
//        } else {
//            if (System.currentTimeMillis() - exitTime > 2000) {
//                Snackbar.make(mFab, "再按一次退出程序", Snackbar.LENGTH_LONG).show();
//                exitTime = System.currentTimeMillis();
//            } else {
//                ActivityManager.getInstance().finishAllActivity();
//                System.exit(0);
//            }
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_meizi) {

            mFragment = GirlsFragment.getInstance();
            mToolbar.setTitle(R.string.meizi);
            replaceFragment();
        } else if (id == R.id.nav_zhihu) {
            mToolbar.setTitle(R.string.zhihu);
            mFragment = ZhihuFragment.getInstance();

            replaceFragment();
        } else if (id == R.id.nav_topnews) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.item_home) {

        } else if (id == R.id.item_category) {

        } else if (id == R.id.item_meizhi) {
            ToastUtil.showShort(this,"meizhi");
        } else if (id == R.id.item_reading) {

        } else if (id == R.id.item_collect) {

        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (mFragment.isAdded()) {
            ft.show(mFragment);
        } else {
            ft.replace(R.id.content_main, mFragment);
        }
        ft.commitAllowingStateLoss();
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //两秒内再次按后退，关闭程序；
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            } else {
                if (System.currentTimeMillis() - exitTime > 2000) {
                    Snackbar.make(mFab, "再按一次退出程序", Snackbar.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    ActivityManager.getInstance().finishAllActivity();
                    System.exit(0);
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
