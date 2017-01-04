package com.naceyeve.liandroid.girl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.LinearLayout;

import com.naceyeve.liandroid.R;
import com.naceyeve.liandroid.app.Constants;
import com.naceyeve.liandroid.base.BaseFragment;
import com.naceyeve.liandroid.bean.Meizi;
import com.naceyeve.liandroid.util.BitmapUtil;
import com.naceyeve.liandroid.widget.PinchImageView;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/30.
 */

public class GirlFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    @Bind(R.id.rootView)
    LinearLayout mRootView;
    private ArrayList<Meizi> mDatas;
    private int mCurrent;
    private GirlAdapter mAdapter;
    private OnGirlChange mLister;

    public static GirlFragment newInstance(ArrayList<Parcelable> data, int current) {
        GirlFragment fragment = new GirlFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("girls", data);
        bundle.putInt("current", current);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        mDatas = bundle.getParcelableArrayList("girls");
        mCurrent = bundle.getInt("current");

        mAdapter = new GirlAdapter(getContext(), mDatas);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(mCurrent);
        mViewPager.setOnPageChangeListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_girl;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //宿主activity要实现onGirlChange
        mLister = (OnGirlChange) activity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        getColor();
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 根据图片获取主题色
     */
    private void getColor() {
        PinchImageView imageView = getCurrentImageView();
        Bitmap bitmap = BitmapUtil.drawableToBitmap(imageView.getDrawable());
        Palette.Builder builder = Palette.from(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vir = palette.getVibrantSwatch();
                if (vir == null) {
                    return;
                }
                //通过回调将fragment中的值传回宿主activity
                mLister.change(vir.getRgb());
            }
        });
    }

    /**
     * 获取当前图片控件
     */
    private PinchImageView getCurrentImageView() {
        View view = mAdapter.getPremaryItem();
        //非空判断
        if (view == null) {
            return null;
        }
        PinchImageView pinchImageView = (PinchImageView) view.findViewById(R.id.img);
        if (pinchImageView == null) {
            return null;
        }
        return pinchImageView;
    }

    public interface OnGirlChange {
        void change(int color);
    }

    /**
     * 保存图片，供宿主调用
     */
    public void saveGirl() {
        String url = mDatas.get(mViewPager.getCurrentItem()).getUrl();
        PinchImageView imageView = getCurrentImageView();
        Bitmap bitmap = BitmapUtil.drawableToBitmap(imageView.getDrawable());
        //图片保存在后台读写线程中进行，完成后转入主线程，采用rxjava链式编程
        Observable.just(BitmapUtil.saveBitmap(bitmap, Constants.dir, url.substring(url.lastIndexOf("/") + 1), true))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean isSuccess) {
                        if (isSuccess) {
                            Snackbar.make(mRootView, "大爷，下载好了哟", Snackbar.LENGTH_LONG).show();
                        } else {
                            Snackbar.make(mRootView, "出错了喵...( ＞ω＜)", Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
        //以下方法为在主线程下载图片，影响用户体验
//        boolean isSuccess = BitmapUtil.saveBitmap(bitmap, Constants.dir, imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.length()), true);
//        if (isSuccess) {
//            Snackbar.make(mRootView, "大爷，下载好了呢~", Snackbar.LENGTH_LONG).show();
//        } else {
//            Snackbar.make(mRootView, "大爷，下载出错了哦~", Snackbar.LENGTH_LONG).show();
//        }


    }

    public void shareGirl(){
        PinchImageView imageView = getCurrentImageView();
        Drawable drawable = imageView.getDrawable();
        if(drawable != null){
            Bitmap bitmap = BitmapUtil.drawableToBitmap(drawable);
            Observable.just(BitmapUtil.saveBitmap(bitmap,Constants.dir,"share.jpg",false))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean iSuccess) {
                            if(iSuccess){
                                Uri uri = Uri.fromFile(new File(Constants.dir, "share.jpg"));
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_SEND);
                                intent.putExtra(Intent.EXTRA_STREAM,uri);
                                intent.setType("image/*");
                                startActivity(Intent.createChooser(intent,"分享Meizi到"));
                            }else {
                                Snackbar.make(mRootView,"出错了喵...( ＞ω＜)",Snackbar.LENGTH_LONG).show();
                            }

                        }
                    });
        }
    }


}
