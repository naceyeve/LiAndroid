package com.naceyeve.liandroid.zhihu;

import com.naceyeve.liandroid.api.ApiCallback;
import com.naceyeve.liandroid.api.SubscriberCallBack;
import com.naceyeve.liandroid.bean.Zhihu;
import com.naceyeve.liandroid.presenter.ImplPresenter.BasePresenterImpl;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/3.
 */

public class ZhihuPresenter extends BasePresenterImpl implements ZhihuContract.Presenter {
    private ZhihuContract.View mView;

    public ZhihuPresenter(ZhihuContract.View view) {
        this.mView = view;
    }

    @Override
    public void getLastNews() {
        Observable<Zhihu> observable = mZhihuApi.getLastDaily();
        addSubscription(toSubscribe(observable, new SubscriberCallBack<Zhihu>(new ApiCallback<Zhihu>() {
            @Override
            public void onSuccess(Zhihu model) {
                mView.load(model);
                mView.showNormal();
            }

            @Override
            public void onFailure(int code, String msg) {
                mView.showError(msg);
            }

            @Override
            public void onCompleted() {

            }
        })));
    }

    @Override
    public void getTheDaily(String date, final Boolean isRefresh) {
        Observable<Zhihu> observable = mZhihuApi.getTheDaily(date);
        addSubscription(toSubscribe(observable, new SubscriberCallBack<Zhihu>(new ApiCallback<Zhihu>() {
            @Override
            public void onSuccess(Zhihu model) {
                if(isRefresh){
                    mView.refresh(model);
                }else {
                    mView.load(model);
                }
                mView.showNormal();

            }

            @Override
            public void onFailure(int code, String msg) {
                mView.showError(msg);
            }

            @Override
            public void onCompleted() {

            }
        })));

    }
}
