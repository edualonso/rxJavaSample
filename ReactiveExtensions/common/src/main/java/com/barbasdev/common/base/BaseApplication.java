package com.barbasdev.common.base;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by edu on 27/11/2016.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
    }
}
