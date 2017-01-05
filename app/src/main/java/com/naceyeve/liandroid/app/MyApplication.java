package com.naceyeve.liandroid.app;

import android.app.Application;

import com.naceyeve.liandroid.util.LogUtil;
import com.naceyeve.liandroid.util.ToastUtil;

/**
 * Created by xinghongfei on 16/8/12.
 */
public class MyApplication extends Application {

    private static MyApplication myApplication;
    public static String currentGirl = "http://ww2.sinaimg.cn/large/610dc034jw1f5k1k4azguj20u00u0421.jpg";

    public static Application getContext() {

        return myApplication;

    }

    @Override
    public void onCreate() {
        super.onCreate();

        myApplication = this;

        LogUtil.isDebug = true; //打印log

        ToastUtil.isShow = true;//显示Toast
        //本地异常退出处理
//        Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(this));

    }


}
