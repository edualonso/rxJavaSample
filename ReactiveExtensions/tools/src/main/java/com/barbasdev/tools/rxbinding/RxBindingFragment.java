package com.barbasdev.tools.rxbinding;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barbasdev.common.base.BaseFragment;
import com.barbasdev.common.datalayer.model.ApiResult;
import com.barbasdev.movies.datamodel.Movie;
import com.barbasdev.movies.datamodel.managers.MoviesManager;
import com.barbasdev.posts.datamodel.Post;
import com.barbasdev.posts.datamodel.managers.PostsManager;
import com.barbasdev.tools.BR;
import com.barbasdev.tools.databinding.FragmentRxbindingBinding;
import com.jakewharton.rxbinding.view.RxView;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by edu on 09/01/2017.
 */

public class RxBindingFragment extends BaseFragment<RxBindingViewModel> {

    private FragmentRxbindingBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRxbindingBinding.inflate(inflater, container, false);
        binding.setVariable(BR.viewModel, viewModel);

        setupRecyclerView(binding.recyclerView);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Observable<List<Movie>> moviesObservable = MoviesManager.getInstance().getTopRatedMovies();
        Observable<List<Post>> postsObservable = PostsManager.getInstance().getResults();

        RxView.clicks(binding.rxBindingButtonTwoObservables)
                .subscribe(aVoid -> {
                    viewModel.clearResults();
                    moviesObservable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(viewModel.getApiResultsSubscriber());
                    postsObservable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(viewModel.getApiResultsSubscriber());
                });

        RxView.clicks(binding.rxBindingButtonMerge)
                .subscribe(aVoid -> {
                    viewModel.clearResults();
                    Observable.merge(moviesObservable, postsObservable)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(viewModel.getApiResultsSubscriber());
                });

        RxView.clicks(binding.rxBindingButtonZip)
                .subscribe(aVoid -> {
                    viewModel.clearResults();
                    Func2<List<? extends ApiResult>, List<? extends ApiResult>, List<? extends ApiResult>> combineLambda = (movieResults, postResults) -> viewModel.getApiResults(movieResults, postResults);
                    Observable.zip(moviesObservable, postsObservable, combineLambda)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(viewModel.getApiResultsSubscriber());
                });
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(viewModel.getAdapter());
    }
}
