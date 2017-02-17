package com.barbasdev.reactiveextensions.main.movies;

import android.os.Bundle;

import com.barbasdev.reactiveextensions.main.base.BaseActivity;

/**
 * Created by edu on 11/02/2017.
 */

public class MoviesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            showFragment(instantiateFragment(this, MoviesFragment.class, getFragmentViewModel()));
        }
    }
}