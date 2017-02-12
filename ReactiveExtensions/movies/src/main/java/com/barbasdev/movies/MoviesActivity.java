package com.barbasdev.movies;

import android.os.Bundle;

import com.barbasdev.common.base.BaseActivity;

/**
 * Created by edu on 11/02/2017.
 */

public class MoviesActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showFragment(instantiateFragment(this, MoviesFragment.class, getFragmentViewModel()));
    }
}
