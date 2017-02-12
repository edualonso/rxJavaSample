package com.barbasdev.movies;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barbasdev.common.base.BaseFragment;
import com.barbasdev.movies.databinding.FragmentMoviesBinding;
import com.barbasdev.movies.datamodel.Movie;
import com.barbasdev.movies.datamodel.managers.MoviesManager;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by edu on 20/11/2016.
 */

public class MoviesFragment extends BaseFragment<MoviesViewModel> {

    private FragmentMoviesBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false);

        binding.setVariable(com.barbasdev.movies.BR.viewModel, viewModel);

        setupRecyclerView(binding.recyclerView);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        RxTextView.textChanges(binding.searchText)
//                .debounce(1000, TimeUnit.MILLISECONDS)
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

        RxTextView.textChanges(binding.searchText)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence charSequence) {
                        Timber.e("Thread: " + Thread.currentThread().getName() + ", FILTERING RESULTS (movies), length: " + charSequence.length());
                        if (charSequence.length() < 3) {
                            viewModel.getAdapter().clearApiResults();
                            return false;
                        }
                        return true;
                    }
                })
                .debounce(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .switchMap(new Func1<CharSequence, Observable<List<Movie>>>() {
                    @Override
                    public Observable<List<Movie>> call(CharSequence charSequence) {
                        Timber.e("Thread: " + Thread.currentThread().getName() + ", PREPARING QUERY (movies): " + charSequence);
                        return MoviesManager.getInstance().getMovieDetailsObservable(charSequence.toString());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Movie>>() {
                    @Override
                    public void onCompleted() {
                        Timber.e("-----------------------> onCompleted (movies)");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("-----------------------> onError (movies): " + e.getMessage());
                    }

                    @Override
                    public void onNext(List<Movie> movies) {
                        for (Movie movie : movies) {
                            Timber.e("Thread: " + Thread.currentThread().getName() + ", Movie: " + movie.getTitle());
                        }

                        viewModel.getAdapter().addApiResults(movies, true);
                    }

                });

//        RxTextView.textChanges(binding.searchText)
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .debounce(1000, TimeUnit.MILLISECONDS)
//                .filter(new Func1<CharSequence, Boolean>() {
//                    @Override
//                    public Boolean call(CharSequence charSequence) {
//                        Timber.e("FILTERING RESULTS (movies), length: " + charSequence.length());
//                        return charSequence.length() >= 3;
//                    }
//                })
//                .switchMap(new Func1<CharSequence, Observable<String>>() {
//                    @Override
//                    public Observable<String> call(CharSequence charSequence) {
//                        Timber.e("PREPARING QUERY (movies): " + charSequence);
//                        List<String> list = new ArrayList<>();
//                        list.add(charSequence.toString());
//                        return Observable.from(list);
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe();
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(viewModel.getAdapter());
    }
}