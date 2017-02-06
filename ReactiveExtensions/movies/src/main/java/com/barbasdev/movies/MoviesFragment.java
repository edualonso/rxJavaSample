package com.barbasdev.movies;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barbasdev.common.base.BaseFragment;
import com.barbasdev.movies.databinding.FragmentMoviesBinding;

/**
 * Created by edu on 20/11/2016.
 */

public class MoviesFragment extends BaseFragment {

    private FragmentMoviesBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false);

        MoviesViewModel viewModel = (MoviesViewModel) super.viewModel;
        viewModel.setText("FIRE REQUEST (MOVIES)");
        binding.setVariable(com.barbasdev.movies.BR.viewModel, viewModel);

        return binding.getRoot();
    }

}
