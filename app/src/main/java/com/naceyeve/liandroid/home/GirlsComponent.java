package com.naceyeve.liandroid.home;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/2/9.
 */
@Subcomponent(modules = GirlsModule.class)
public interface GirlsComponent {
    GirlsFragment inject(GirlsFragment girlsFragment);
}
