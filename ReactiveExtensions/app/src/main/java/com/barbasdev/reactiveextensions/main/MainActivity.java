package com.barbasdev.reactiveextensions.main;

import android.os.Bundle;

import com.barbasdev.common.base.BaseActivity;
import com.barbasdev.tools.rxbinding.RxBindingFragment;
import com.barbasdev.tools.rxbinding.RxBindingViewModel;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showFragment(instantiateFragment(this, RxBindingFragment.class, new RxBindingViewModel(this)));
    }
}
