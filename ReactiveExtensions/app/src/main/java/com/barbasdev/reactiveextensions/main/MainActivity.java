package com.barbasdev.reactiveextensions.main;

import android.os.Bundle;

import com.barbasdev.reactiveextensions.main.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showFragment(instantiateFragment(this, MainFragment.class));
    }
}
