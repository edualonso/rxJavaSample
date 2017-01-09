package com.barbasdev.tools.rxbinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barbasdev.common.base.BaseFragment;
import com.barbasdev.movies.datamodel.Movie;
import com.barbasdev.movies.datamodel.MovieResults;
import com.barbasdev.movies.datamodel.managers.MoviesManager;
import com.barbasdev.posts.datamodel.Post;
import com.barbasdev.posts.datamodel.managers.PostsManager;
import com.barbasdev.tools.R;
import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by edu on 09/01/2017.
 */

public class RxBindingFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rxbinding, container, false);

        RxBindingViewModel viewModel = (RxBindingViewModel) super.viewModel;
        viewModel.setText("RXBINDING");
        binding.setVariable(com.barbasdev.tools.BR.viewModel, viewModel);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        RxView.clicks(view.findViewById(R.id.rxBindingButton))
//                .subscribe(nothing -> {
//                    Timber.e("CLICK!");
//                });

//        List<String> urls = new ArrayList<>();
//        urls.add("www.google.com");
//        urls.add("www.facebook.com");
//        urls.add("www.linkedin.com");
//        Observable<String> urlsObservable = Observable.from(urls);
//
//        RxView.clicks(view.findViewById(R.id.rxBindingButton))
//                .subscribe(nothing -> {
//                    urlsObservable.subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(new Observer<String>() {
//                                @Override
//                                public void onCompleted() {
//                                    Timber.e("onCompleted!");
//                                }
//
//                                @Override
//                                public void onError(Throwable e) {
//                                    Timber.e("onError!");
//                                }
//
//                                @Override
//                                public void onNext(String s) {
//                                    Timber.e("onNext! " + s);
//                                }
//                            });
//                });


//        Observable<MovieResults> movieResultsObservable = MoviesManager.getInstance().getResults();
//        RxView.clicks(view.findViewById(R.id.rxBindingButton))
//                .subscribe(nothing -> {
//                    movieResultsObservable.subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(new Subscriber<MovieResults>() {
//                                @Override
//                                public void onCompleted() {
//                                    Timber.e("onCompleted!");
//                                }
//
//                                @Override
//                                public void onError(Throwable e) {
//                                    Timber.e("onError!");
//                                }
//
//                                @Override
//                                public void onNext(MovieResults movieResults) {
//                                    for (Movie movie : movieResults.getResults()) {
//                                        Timber.e("Movie name: " + movie.getTitle());
//                                    }
//                                }
//                            });
//                });


//        Observable<MovieResults> movieResultsObservable = MoviesManager.getInstance().getResults();
//        RxView.clicks(view.findViewById(R.id.rxBindingButton))
//                .flatMap(aVoid -> Observable.zip(
//                        movieResultsObservable,
//                        movieResultsObservable,
//                        new Func2<MovieResults, MovieResults, Integer>() {
//                            @Override
//                            public Integer call(MovieResults movieResults, MovieResults movieResults2) {
//                                return movieResults.getResults().size() + movieResults2.getResults().size();
//                            }
//                        }));

        Observable<MovieResults> movieResultsObservable = MoviesManager.getInstance().getResults();
        Observable<List<Post>> postResultsObservable = PostsManager.getInstance().getResults();
        Func2<MovieResults, List<Post>, List<String>> zipFunction = new Func2<MovieResults, List<Post>, List<String>>() {
            @Override
            public List<String> call(MovieResults movieResults, List<Post> postResults) {
                return getMoviesAndPosts(movieResults, postResults);
            }
        };
        Observer<List<String>> movieTitleObserver = new Observer<List<String>>() {
            @Override
            public void onCompleted() {
                Timber.e("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Timber.e("onError");
            }

            @Override
            public void onNext(List<String> titles) {
                for (String title : titles) {
                    Timber.e("Title: " + title);
                }
            }
        };

        RxView.clicks(view.findViewById(R.id.rxBindingButton))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Observable.zip(movieResultsObservable, postResultsObservable, zipFunction)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(movieTitleObserver);
                    }
                });
    }

    @NonNull
    private List<String> getMoviesAndPosts(MovieResults movieResults, List<Post> postResults) {
        List<String> moviesAndPosts = new ArrayList<>();
        for (Movie movie : movieResults.getResults()) {
            moviesAndPosts.add(movie.getTitle());
        }
        moviesAndPosts.add("*** -------------------- *** -------------------- ***");
        for (Post post : postResults) {
            moviesAndPosts.add(post.getTitle());
        }
        return moviesAndPosts;
    }
}
