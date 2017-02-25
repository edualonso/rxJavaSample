package com.barbasdev.reactiveextensions.main.base;

import android.app.Application;

import com.barbasdev.reactiveextensions.main.di.DaggerDependencyGraphComponent;
import com.barbasdev.reactiveextensions.main.di.DependencyGraphComponent;
import com.barbasdev.reactiveextensions.main.di.MainModule;

import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by edu on 27/11/2016.
 */

public class BaseApplication extends Application {

    private static DependencyGraphComponent graph;

    public static DependencyGraphComponent getGraph() {
        return graph;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
        Realm.init(this);

//        graph = DependencyGraphComponent.Initializer.init(this);
        graph = DaggerDependencyGraphComponent.builder()
                .mainModule(new MainModule(this))
                .build();
    }
}