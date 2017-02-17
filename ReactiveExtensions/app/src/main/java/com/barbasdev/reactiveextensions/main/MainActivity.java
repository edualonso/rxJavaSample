package com.barbasdev.reactiveextensions.main;

import android.os.Bundle;

import com.barbasdev.reactiveextensions.main.base.BaseActivity;
import com.barbasdev.reactiveextensions.main.base.BaseApplication;
import com.barbasdev.reactiveextensions.main.di.HelloService;

import javax.inject.Inject;

import timber.log.Timber;

public class MainActivity extends BaseActivity {

    @Inject
    HelloService helloService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showFragment(instantiateFragment(this, MainFragment.class));

        Timber.e(helloService.greet("MainActivity: LOLOLOLO"));
    }

    @Override
    public void performDependencyInjection() {
        BaseApplication.getGraph().inject(this);
    }
}
