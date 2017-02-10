package com.naceyeve.liandroid.home;

import com.naceyeve.liandroid.api.ApiManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/2/9.
 */
@Module
public class GirlsModule {
    private  GirlsContract.View mView;

    public GirlsModule(GirlsContract.View view){
        this.mView = view;
    }
    @Provides
    GirlsContract.View provideGirlsContractView(){
        return mView;
    }
    @Provides
    GirlsPresenter provideGirlsContractPresenter(ApiManager apiManager){
        return  new GirlsPresenter(mView,apiManager);
    }

}
