package com.naceyeve.liandroid.app;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wanglj on 16/7/4.
 */
@Module
public class AppModule {
    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

//    @Provides
//    @Singleton
//    public PreferencesManager provideSharedPreferences(){
//        return  new PreferencesManager(application);
//    }


}
