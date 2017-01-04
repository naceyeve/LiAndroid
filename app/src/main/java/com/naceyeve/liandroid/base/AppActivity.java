package com.naceyeve.liandroid.base;

import android.content.Intent;
import android.os.Bundle;


/**
 * Created by renlei on 2016/5/23.
 */
public abstract class AppActivity extends BaseActivity {

    //获取第一个fragment
    protected abstract BaseFragment getFirstFragment();

    //获取Intent
    protected void handleIntent(Intent intent) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        if (null != getIntent()) {
            handleIntent(getIntent());
        }
        //避免重复添加Fragment
        if (null == getSupportFragmentManager().getFragments()) {
            BaseFragment firstFragment = getFirstFragment();
            if (null != firstFragment) {
                addFragment(firstFragment);
            }
        }

        ActivityManager.getInstance().addActivity(this);
    }

    @Override
    protected abstract int getContentViewId();

    @Override
    protected abstract int getFragmentContentId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().finishActivity(this);
    }
}
