package com.barbasdev.reactiveextensions.main.posts;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barbasdev.reactiveextensions.BR;
import com.barbasdev.reactiveextensions.R;
import com.barbasdev.reactiveextensions.databinding.FragmentPostsBinding;
import com.barbasdev.reactiveextensions.main.base.BaseFragment;

/**
 * Created by edu on 20/11/2016.
 */

public class PostsFragment extends BaseFragment<PostsViewModel, FragmentPostsBinding> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_posts, container, false);

        viewModel.setText("FIRE REQUEST (POSTS)");
        binding.setVariable(BR.viewModel, viewModel);

        return binding.getRoot();
    }

    @Override
    protected void setupViewModel() {
        viewModel.setup();
    }
}