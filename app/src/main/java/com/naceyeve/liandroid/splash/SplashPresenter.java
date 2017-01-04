package com.naceyeve.liandroid.splash;

import android.content.Context;

import com.naceyeve.liandroid.app.MyApplication;
import com.naceyeve.liandroid.api.ApiCallback;
import com.naceyeve.liandroid.api.ApiResponse;
import com.naceyeve.liandroid.api.SubscriberCallBack;
import com.naceyeve.liandroid.bean.Meizi;
import com.naceyeve.liandroid.presenter.ImplPresenter.BasePresenterImpl;
import com.naceyeve.liandroid.util.CacheUtil;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2016/12/29.
 */

public class SplashPresenter extends BasePresenterImpl implements SplashContract.Presenter{
    private SplashFragment mView;
    private CacheUtil mCacheUtil;

    public SplashPresenter(Context context,SplashFragment view) {
        this.mView = view;
        mCacheUtil = CacheUtil.get(context);
    }

    @Override
    public void getGirl(int id) {
        mView.showProgressDialog();
        Observable<ApiResponse<List<Meizi>>> observable = mApi.getGirls("福利", 10, id);
        addSubscription(toSubscribe(observable,
                new SubscriberCallBack<ApiResponse<List<Meizi>>>(new ApiCallback<ApiResponse<List<Meizi>>>() {
                    @Override
                    public void onSuccess(ApiResponse<List<Meizi>> model) {
                        mView.hidProgressDialog();
                        mView.showGirl(model.getResults().get(0).getUrl());
                        MyApplication.currentGirl = model.getResults().get(0).getUrl();
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        mView.showGirl();
                    }

                    @Override
                    public void onCompleted() {

                    }
                })));
    }
}
