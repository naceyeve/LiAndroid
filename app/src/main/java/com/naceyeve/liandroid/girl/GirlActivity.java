package com.naceyeve.liandroid.girl;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.naceyeve.liandroid.R;
import com.naceyeve.liandroid.base.AppActivity;
import com.naceyeve.liandroid.base.BaseFragment;
import com.naceyeve.liandroid.util.ColorUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/30.
 */

public class GirlActivity extends AppActivity implements GirlFragment.OnGirlChange {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    private GirlFragment mGirlFragment;


    @Override
    protected BaseFragment getFirstFragment() {
        mGirlFragment = GirlFragment.newInstance(getIntent().getParcelableArrayListExtra("girls"), getIntent().getIntExtra("current", 0));
        return mGirlFragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_girl;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.girl_fragment;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mToolbar.setTitle(R.string.meizi);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishWithAnim();
            }
        });
    }

    private void finishWithAnim() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();//配合完成共享元素过渡动画
        }else {
            finish();
        }
        overridePendingTransition(R.anim.slide_left_in,R.anim.slide_right_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_girl,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                mGirlFragment.saveGirl();
                break;
            case R.id.action_share:
                mGirlFragment.shareGirl();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            finishWithAnim();
            return true;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public void change(int color) {
        mToolbar.setBackgroundColor(color);
        if(Build.VERSION.SDK_INT > 21){
            Window window = getWindow();
            window.setStatusBarColor(ColorUtil.colorBurn(color));
            window.setNavigationBarColor(ColorUtil.colorBurn(color));
        }
    }
}
