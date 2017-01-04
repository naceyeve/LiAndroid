package com.naceyeve.liandroid.zhihu.zhihuDescriber;

import com.naceyeve.liandroid.api.ApiCallback;
import com.naceyeve.liandroid.api.SubscriberCallBack;
import com.naceyeve.liandroid.bean.ZhihuDescriber;
import com.naceyeve.liandroid.presenter.ImplPresenter.BasePresenterImpl;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/3.
 */

public class ZhihuDescriberPresenter extends BasePresenterImpl implements ZhihuDescriberContract.Presenter {
    private ZhihuDescriberContract.View mView;
    public ZhihuDescriberPresenter(ZhihuDescriberContract.View view) {
        this.mView = view;
    }

    @Override
    public void getHtml(int id) {
        Observable<ZhihuDescriber> observable = mZhihuApi.getZhihuStory(id + "");
        addSubscription(toSubscribe(observable,new SubscriberCallBack<ZhihuDescriber>(new ApiCallback<ZhihuDescriber>() {
            @Override
            public void onSuccess(ZhihuDescriber model) {
                mView.showHtml(model);
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
