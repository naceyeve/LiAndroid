package com.naceyeve.liandroid.home;

import com.naceyeve.liandroid.bean.Meizi;
import com.naceyeve.liandroid.presenter.IBasePresenter;
import com.naceyeve.liandroid.presenter.ImplView.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/29.
 */

public interface GirlsContract {
    interface View extends IBaseView{
        void refresh(List<Meizi> datas);

        void load(List<Meizi> datas);

        void showNormal();
    }

    interface Presenter extends IBasePresenter{
        void getGirls(int page,int size,Boolean isRefresh);
    }
}
