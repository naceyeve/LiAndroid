package com.naceyeve.liandroid.zhihu.zhihuDescriber;

import com.naceyeve.liandroid.bean.ZhihuDescriber;
import com.naceyeve.liandroid.presenter.IBasePresenter;
import com.naceyeve.liandroid.presenter.ImplView.IBaseView;

/**
 * Created by Administrator on 2017/1/3.
 */

public interface ZhihuDescriberContract {
    interface View extends IBaseView{
        void showHtml(ZhihuDescriber model);
    }
    interface Presenter extends IBasePresenter{
        void getHtml(int id);
    }
}
