package com.barbasdev.posts;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barbasdev.common.base.BaseFragment;
import com.barbasdev.posts.databinding.FragmentPostsBinding;

/**
 * Created by edu on 20/11/2016.
 */

public class PostsFragment extends BaseFragment {

    private FragmentPostsBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_posts, container, false);

        PostsViewModel viewModel = (PostsViewModel) super.viewModel;
        viewModel.setText("FIRE REQUEST (POSTS)");
        binding.setVariable(com.barbasdev.posts.BR.viewModel, viewModel);

        return binding.getRoot();
    }

}
