package com.barbasdev.reactiveextensions.main.movies;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barbasdev.reactiveextensions.BR;
import com.barbasdev.reactiveextensions.R;
import com.barbasdev.reactiveextensions.databinding.FragmentMoviesBinding;
import com.barbasdev.reactiveextensions.main.base.BaseFragment;
import com.jakewharton.rxbinding2.widget.RxTextView;

/**
 * Created by edu on 20/11/2016.
 */

public class MoviesFragment extends BaseFragment<MoviesViewModel, FragmentMoviesBinding> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false);

        binding.setVariable(BR.viewModel, viewModel);

        setupRecyclerView(binding.recyclerView);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.queryMovie(RxTextView.textChanges(binding.searchText));
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(viewModel.getAdapter());
    }

    @Override
    protected void setupViewModel() {
        viewModel.setup();
    }
}