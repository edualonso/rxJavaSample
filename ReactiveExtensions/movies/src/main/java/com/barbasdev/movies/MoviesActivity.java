package com.barbasdev.movies;

import android.os.Bundle;

import com.barbasdev.common.base.BaseActivity;

/**
 * Created by edu on 11/02/2017.
 */

public class MoviesActivity extends BaseActivity {

    @Override
    public void injectDependencies() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            showFragment(instantiateFragment(this, MoviesFragment.class, getFragmentViewModel()));
        }
    }
}
