package com.naceyeve.liandroid.presenter.ImplPresenter;


import com.google.gson.Gson;
import com.naceyeve.liandroid.api.ApiManage;
import com.naceyeve.liandroid.api.ApiStore;
import com.naceyeve.liandroid.presenter.IBasePresenter;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by 蔡小木 on 2016/4/29 0029.
 */
public class BasePresenterImpl implements IBasePresenter {

    protected final ApiStore mApi = ApiManage.getInstence().getGankService();
    protected final ApiStore mZhihuApi = ApiManage.getInstence().getZhihuApiService();

    protected Gson gson = new Gson();
    private CompositeSubscription mCompositeSubscription;

    protected void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    @Override
    public void unsubcrible() {

        // TODO: 16/8/17 find when unsubcrible
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }

    protected  <T> Subscription toSubscribe(Observable<T> o, Subscriber<T> s){
        Subscription subscription = o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
        return subscription;
    }
}
