package com.naceyeve.liandroid.zhihu;

import com.naceyeve.liandroid.bean.Zhihu;
import com.naceyeve.liandroid.presenter.IBasePresenter;
import com.naceyeve.liandroid.presenter.ImplView.IBaseView;

/**
 * Created by Administrator on 2017/1/3.
 */

public interface ZhihuContract {
    interface View extends IBaseView{
        void refresh(Zhihu datas);

        void load(Zhihu datas);

        void showNormal();
    }

    interface Presenter extends IBasePresenter{
        void getLastNews();

        void getTheDaily(String date, Boolean isRefresh);
    }
}
