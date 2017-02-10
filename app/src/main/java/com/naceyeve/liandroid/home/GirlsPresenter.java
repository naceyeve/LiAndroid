package com.naceyeve.liandroid.home;

import com.naceyeve.liandroid.api.ApiCallback;
import com.naceyeve.liandroid.api.ApiResponse;
import com.naceyeve.liandroid.api.SubscriberCallBack;
import com.naceyeve.liandroid.bean.Meizi;
import com.naceyeve.liandroid.presenter.ImplPresenter.BasePresenterImpl;
import com.naceyeve.liandroid.api.ApiManager;

import java.util.List;

/**
 * Created by Administrator on 2016/12/29.
 */

public class GirlsPresenter extends BasePresenterImpl implements GirlsContract.Presenter {
    private GirlsContract.View mView;
    public GirlsPresenter(GirlsContract.View view) {
        this.mView = view;
    }
    private ApiManager mApiManager;
    public GirlsPresenter(GirlsContract.View view,ApiManager apiManager) {
        this.mView = view;
        this.mApiManager = apiManager;
    }

    @Override
    public void getGirls(int page, int size, final Boolean isRefresh) {
//        Observable<ApiResponse<List<Meizi>>> observable = mApi.getGirls("福利", size, page);
//        addSubscription(toSubscribe(observable,
//                new SubscriberCallBack<ApiResponse<List<Meizi>>>(new ApiCallback<ApiResponse<List<Meizi>>>() {
//                    @Override
//                    public void onSuccess(ApiResponse<List<Meizi>> model) {
//                        if(isRefresh){
//                            mView.refresh(model.getResults());
//                        }else {
//                            mView.load(model.getResults());
//                        }
//                        mView.showNormal();
//                    }
//
//                    @Override
//                    public void onFailure(int code, String msg) {
//                        mView.showError(msg);
//                    }
//
//                    @Override
//                    public void onCompleted() {
//
//                    }
//                })));
        mApiManager.getGirls(size,page,new SubscriberCallBack<>(new ApiCallback<ApiResponse<List<Meizi>>>() {
            @Override
            public void onSuccess(ApiResponse<List<Meizi>> model) {
                if(isRefresh){
                    mView.refresh(model.getResults());
                }else {
                    mView.load(model.getResults());
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
        }));
    }
}
