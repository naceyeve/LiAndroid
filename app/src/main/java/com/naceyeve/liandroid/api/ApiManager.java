package com.naceyeve.liandroid.api;

import android.app.Application;

import com.naceyeve.liandroid.api.ApiStore;
import com.naceyeve.liandroid.api.SubscriberCallBack;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wanglj on 16/7/4.
 */

public class ApiManager {

    private final ApiStore apiService;

    private final Application application;

    public ApiManager(ApiStore apiService, Application application) {
        this.apiService = apiService;
        this.application = application;
    }

//    public void login(String username, String password, SimpleCallback<User> simpleCallback){
//         apiService.login(username,password)
//                 .flatMap(new BaseResponseFunc<User>())
//                 .subscribeOn(Schedulers.io())
//                 .observeOn(AndroidSchedulers.mainThread())
//                 .subscribe(new ExceptionSubscriber<User>(simpleCallback,application));
//    }

    protected  <T> Subscription toSubscribe(Observable<T> o, Subscriber<T> s){
        Subscription subscription = o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
        return subscription;
    }
    public void getGirls(int size,int page,SubscriberCallBack callBack){
        toSubscribe(apiService.getGirls("福利", size, page),callBack);
    }

}
