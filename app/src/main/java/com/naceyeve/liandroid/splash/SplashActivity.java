package com.naceyeve.liandroid.splash;

import android.view.View;

import com.naceyeve.liandroid.R;
import com.naceyeve.liandroid.base.AppActivity;
import com.naceyeve.liandroid.base.BaseFragment;

/**
 * Created by Administrator on 2016/12/29.
 */

public class SplashActivity extends AppActivity {
    @Override
    protected BaseFragment getFirstFragment() {
        return SplashFragment.getInstance();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.splash_fragment;
    }

    @Override
    public void onClick(View view) {

    }
}
