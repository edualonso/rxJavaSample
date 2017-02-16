package com.barbasdev.reactiveextensions.main;

import android.os.Bundle;

import com.barbasdev.common.base.BaseActivity;

public class MainActivity extends BaseActivity {

//    @Inject
//    public HelloService helloService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showFragment(instantiateFragment(this, MainFragment.class));

//        BaseApplication.getGraph().inject(this);
//        Timber.e(helloService.greet("LOLOLOLO"));
    }
}
