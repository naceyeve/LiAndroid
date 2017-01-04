package com.naceyeve.liandroid.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.naceyeve.liandroid.R;
import com.naceyeve.liandroid.base.ActivityManager;
import com.naceyeve.liandroid.base.BaseFragment;
import com.naceyeve.liandroid.home.HomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/29.
 */

public class SplashFragment extends BaseFragment implements SplashContract.View{

    @Bind(R.id.splash)
    ImageView mSplash;
    private SplashPresenter mPresenter;
    private ScaleAnimation scaleAnimation;

    //获取实例
    public static SplashFragment getInstance() {
        SplashFragment splashFragment = new SplashFragment();
        return splashFragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        mPresenter = new SplashPresenter(getContext(),SplashFragment.this);
        initAnim();
    }
    private void initAnim() {
        scaleAnimation = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(2500);
        mSplash.startAnimation(scaleAnimation);

        //缩放动画监听
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(getActivity(), HomeActivity.class));
                ActivityManager.getInstance().finishActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_splash;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void showGirl(String url) {
        Glide.with(getActivity()).load(url).into(mSplash);
    }

    @Override
    public void showGirl() {
        Glide.with(getContext())
                .load(R.mipmap.welcome)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .animate(scaleAnimation)
                .into(mSplash);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hidProgressDialog() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getGirl(1);
    }
}
