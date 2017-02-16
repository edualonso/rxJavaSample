package com.barbasdev.common.base;

import android.app.Application;

import com.barbasdev.common.di.DaggerGraphComponent;

import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by edu on 27/11/2016.
 */

public class BaseApplication extends Application {

    private static DaggerGraphComponent graph;

    public static DaggerGraphComponent getGraph() {
        return graph;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
        Realm.init(this);
        graph = DaggerGraphComponent.Initializer.init(this);
    }
}
