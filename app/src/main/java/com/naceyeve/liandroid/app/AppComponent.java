package com.naceyeve.liandroid.app;

import com.naceyeve.liandroid.api.ApiModule;
import com.naceyeve.liandroid.home.GirlsComponent;
import com.naceyeve.liandroid.home.GirlsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by wanglj on 16/7/4.
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {
    GirlsComponent plus(GirlsModule girlsModule);
}
