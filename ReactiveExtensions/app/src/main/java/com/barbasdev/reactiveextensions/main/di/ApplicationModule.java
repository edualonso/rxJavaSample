package com.barbasdev.reactiveextensions.main.di;

import android.app.Application;

import com.barbasdev.common.di.HelloService;
import com.barbasdev.common.di.HelloServiceManager;
import com.barbasdev.reactiveextensions.main.MainApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private MainApplication application;

    public ApplicationModule(MainApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    protected Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    HelloService provideHelloService() {
        return new HelloServiceManager();
    }
}