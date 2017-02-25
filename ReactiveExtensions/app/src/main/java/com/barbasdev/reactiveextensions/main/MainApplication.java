package com.barbasdev.reactiveextensions.main;

import com.barbasdev.common.base.BaseApplication;
import com.barbasdev.reactiveextensions.main.di.ApplicationComponent;
import com.barbasdev.reactiveextensions.main.di.ApplicationModule;
import com.barbasdev.reactiveextensions.main.di.DaggerApplicationComponent;

/**
 * Created by edu on 27/11/2016.
 */

public class MainApplication extends BaseApplication {

    private static ApplicationComponent applicationComponent;

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}