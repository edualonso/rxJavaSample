package com.barbasdev.reactiveextensions.main;

import android.os.Bundle;

import com.barbasdev.common.base.BaseActivity;
import com.barbasdev.movies.MoviesFragment;
import com.barbasdev.movies.MoviesViewModel;
import com.barbasdev.posts.PostsFragment;
import com.barbasdev.posts.PostsViewModel;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showFragmentTop(instantiateFragment(this, MoviesFragment.class, new MoviesViewModel(this)));
        showFragmentBottom(instantiateFragment(this, PostsFragment.class, new PostsViewModel(this)));
    }
}
