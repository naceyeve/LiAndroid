package com.naceyeve.liandroid.recently;

import com.naceyeve.liandroid.api.ApiCallback;
import com.naceyeve.liandroid.api.ApiResponse;
import com.naceyeve.liandroid.api.SubscriberCallBack;
import com.naceyeve.liandroid.bean.Ganhuo;
import com.naceyeve.liandroid.bean.RecentlyWrapper;
import com.naceyeve.liandroid.presenter.ImplPresenter.BasePresenterImpl;

import java.util.List;

import rx.Observable;
import rx.functions.Func2;

/**
 * Created by Administrator on 2017/1/5.
 */

public class RecentlyPresenter extends BasePresenterImpl implements RecentlyContract.Presenter {
    private RecentlyContract.View mView;
    public RecentlyPresenter (RecentlyContract.View view){
        this.mView = view;
    }
    @Override
    public void getRecentlyData() {
        mView.showProgressDialog();
//        Observable<ApiResponse<List<Ganhuo>>> observable = mApi.getRecentlyData();
        Observable<RecentlyWrapper> observable = mApi.getRecentlyData().zipWith(mApi.getDate(), new Func2<ApiResponse<List<Ganhuo>>, ApiResponse<List<String>>, RecentlyWrapper>() {
            @Override
            public RecentlyWrapper call(ApiResponse<List<Ganhuo>> listApiResponse, ApiResponse<List<String>> listApiResponse2) {
                RecentlyWrapper wrapper = new RecentlyWrapper();
                wrapper.datalist = listApiResponse.getResults();
                wrapper.titlelist = listApiResponse2.getResults();
                return wrapper;
            }
        });
        addSubscription(toSubscribe(observable,new SubscriberCallBack<RecentlyWrapper>(new ApiCallback<RecentlyWrapper>() {
            @Override
            public void onSuccess(RecentlyWrapper model) {
                mView.setRecentlyData(model);
                mView.hidProgressDialog();
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
