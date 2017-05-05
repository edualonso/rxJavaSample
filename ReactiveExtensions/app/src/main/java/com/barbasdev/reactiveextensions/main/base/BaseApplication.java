package com.barbasdev.reactiveextensions.main.base;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by edu on 27/11/2016.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
    }
}