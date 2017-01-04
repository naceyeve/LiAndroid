package com.naceyeve.liandroid.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.naceyeve.liandroid.app.MyApplication;
import com.naceyeve.liandroid.R;
import com.naceyeve.liandroid.base.BaseFragment;
import com.naceyeve.liandroid.base.GestureActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by Administrator on 2016/12/30.
 */

public class AboutActivity extends GestureActivity {


    @Bind(R.id.backdrop)
    ImageView mBackdrop;
    @Bind(R.id.about_toolbar)
    Toolbar mAboutToolbar;

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }

    @Override
    protected void doFinish() {
        finishWithAnim();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_about;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
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
        mAboutToolbar.setTitle("关于我");
        Glide.with(this)
                .load(MyApplication.currentGirl)
                .bitmapTransform(new BlurTransformation(this, 15))//毛玻璃效果
                .into(mBackdrop);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            finishWithAnim();
            return true;//返回true 处理结果
        }else {

            return super.onKeyDown(keyCode, event);
        }
    }

    private void finishWithAnim() {
        finish();
        overridePendingTransition(R.anim.slide_left_in,R.anim.slide_right_out);
    }
}
