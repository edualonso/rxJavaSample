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

//        RxTextView.textChanges(binding.searchText)
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .filter(new Func1<CharSequence, Boolean>() {
//                    @Override
//                    public Boolean call(CharSequence charSequence) {
//                        Timber.e("Thread: " + Thread.currentThread().getName() + ", FILTERING RESULTS (movies), length: " + charSequence.length());
//                        if (charSequence.length() < 3) {
//                            viewModel.getAdapter().clearApiResults();
//                            return false;
//                        }
//                        return true;
//                    }
//                })
//                .debounce(1000, TimeUnit.MILLISECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .switchMap(new Func1<CharSequence, Observable<List<Movie>>>() {
//                    @Override
//                    public Observable<List<Movie>> call(CharSequence charSequence) {
//                        Timber.e("Thread: " + Thread.currentThread().getName() + ", PREPARING QUERY (movies): " + charSequence);
//                        return MoviesManager.getInstance().getMovieDetailsObservable(charSequence.toString());
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<List<Movie>>() {
//                    @Override
//                    public void onCompleted() {
//                        Timber.e("-----------------------> onCompleted (movies)");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Timber.e("-----------------------> onError (movies): " + e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(List<Movie> movies) {
//                        for (Movie movie : movies) {
//                            Timber.e("Thread: " + Thread.currentThread().getName() + ", Movie: " + movie.getTitle());
//                        }
//
//                        viewModel.getAdapter().addApiResults(movies, true);
//                    }
//
//                });
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