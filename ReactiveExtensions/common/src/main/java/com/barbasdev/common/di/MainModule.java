//package com.barbasdev.common.di;
//
//import android.app.Application;
//
//import com.barbasdev.common.base.BaseApplication;
//
//import javax.inject.Singleton;
//
//import dagger.Module;
//import dagger.Provides;
//
//@Module
//public class MainModule {
//    BaseApplication application;
//
//    public MainModule(BaseApplication application) {
//        this.application = application;
//    }
//
//    @Provides
//    @Singleton
//    protected Application provideApplication() {
//        return application;
//    }
//
//    @Provides
//    @Singleton
//    HelloService provideHelloService() {
//        return new HelloServiceManager();
//    }
//}