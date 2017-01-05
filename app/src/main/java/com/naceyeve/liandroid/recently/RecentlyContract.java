package com.naceyeve.liandroid.recently;

import com.naceyeve.liandroid.bean.RecentlyWrapper;
import com.naceyeve.liandroid.presenter.IBasePresenter;
import com.naceyeve.liandroid.presenter.ImplView.IBaseView;

/**
 * Created by Administrator on 2017/1/5.
 */

public interface RecentlyContract {
    interface View extends IBaseView{
        void setRecentlyData(RecentlyWrapper data);
    }

    interface Presenter extends IBasePresenter{
        void getRecentlyData();
    }
}
