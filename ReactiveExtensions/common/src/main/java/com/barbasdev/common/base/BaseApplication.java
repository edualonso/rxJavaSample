package com.barbasdev.common.base;

import android.app.Application;

import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by edu on 27/11/2016.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        Realm.init(this);
    }
}
