package com.naceyeve.liandroid.splash;

import com.naceyeve.liandroid.presenter.IBasePresenter;
import com.naceyeve.liandroid.presenter.ImplView.IBaseView;

/**
 * Created by Administrator on 2016/12/29.
 */

public interface SplashContract {

    interface View extends IBaseView{
        void showGirl(String url);
        void showGirl();
    }

    interface Presenter extends IBasePresenter {
        void getGirl(int id);
    }
}
