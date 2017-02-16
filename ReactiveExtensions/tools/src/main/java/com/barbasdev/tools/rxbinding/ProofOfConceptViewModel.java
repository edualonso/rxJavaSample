package com.barbasdev.tools.rxbinding;

import android.os.Parcel;
import android.support.annotation.NonNull;

import com.barbasdev.common.base.BaseViewModel;
import com.barbasdev.common.datalayer.model.ApiResult;
import com.barbasdev.common.datalayer.model.ApiResultAdapter;
import com.barbasdev.movies.datamodel.Movie;
import com.barbasdev.movies.datamodel.managers.MoviesManager;
import com.barbasdev.posts.datamodel.Post;
import com.barbasdev.posts.datamodel.managers.PostsManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by edu on 09/01/2017.
 */

public class ProofOfConceptViewModel<T extends ApiResult> extends BaseViewModel {

    private ApiResultAdapter adapter;
    private Observable<List<Movie>> moviesObservable;
    private Observable<List<Post>> postsObservable;

    public ProofOfConceptViewModel() {

    }

    @Override
    public void setup() {
        adapter = new ApiResultAdapter();
    }

    @NonNull
    public List<T> getApiResultsUnbound(List<T> movieResults, List<T> postResults) {
        List<T> results = new ArrayList<>();
        results.addAll(movieResults);
        results.addAll(postResults);
        return results;
    }

    @NonNull
    private List<ApiResult> getApiResultsBound(List<Movie> movies, List<Post> posts) {
        List<ApiResult> results = new ArrayList<>();
        results.addAll(movies);
        results.addAll(posts);
        return results;
    }

    public void clearResults() {
        adapter.clearApiResults();
    }

    public ApiResultAdapter<T> getAdapter() {
        return adapter;
    }

    public void initResultsObservables() {
        moviesObservable = MoviesManager.getInstance().getTopRatedMovies();
        postsObservable = PostsManager.getInstance().getResults();
    }

    public void triggerTwoObservables(Observable<Object> observable) {
        observable.subscribe(aVoid -> {
            clearResults();
            moviesObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<Movie>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Timber.e("Thread: " + Thread.currentThread().getName() + ", moviesObservable - onSubscribe");
                        }

                        @Override
                        public void onNext(List<Movie> movies) {
                            Timber.e("Thread: " + Thread.currentThread().getName() + ", moviesObservable - onNext, timestamp: " + System.currentTimeMillis());
                            adapter.addApiResults(movies, false);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Timber.e("Thread: " + Thread.currentThread().getName() + ", moviesObservable - onError: " + e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            Timber.e("Thread: " + Thread.currentThread().getName() + ", moviesObservable - onComplete");
                        }
                    });
            postsObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<Post>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Timber.e("Thread: " + Thread.currentThread().getName() + ", postsObservable - onSubscribe");
                        }

                        @Override
                        public void onNext(List<Post> posts) {
                            Timber.e("Thread: " + Thread.currentThread().getName() + ", postsObservable - onNext, timestamp: " + System.currentTimeMillis());
                            adapter.addApiResults(posts, false);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Timber.e("Thread: " + Thread.currentThread().getName() + ", postsObservable - onError: " + e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            Timber.e("Thread: " + Thread.currentThread().getName() + ", postsObservable - onComplete");
                        }
                    });
        });
    }

    public void triggerMergeObservables(Observable<Object> observable) {
        observable.subscribe(aVoid -> {
            clearResults();
            Observable.merge(moviesObservable, postsObservable)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<? extends ApiResult>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Timber.e("Thread: " + Thread.currentThread().getName() + ", merge observable - onSubscribe");
                        }

                        @Override
                        public void onNext(List<? extends ApiResult> results) {
                            Timber.e("Thread: " + Thread.currentThread().getName() + ", merge observable - onNext, timestamp: " + System.currentTimeMillis());
                            adapter.addApiResults(results, false);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Timber.e("Thread: " + Thread.currentThread().getName() + ", merge observable - onError: " + e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            Timber.e("Thread: " + Thread.currentThread().getName() + ", merge observable - onComplete");
                        }
                    });
        });
    }

    public void triggerZipObservables(Observable<Object> observable) {
        observable.subscribe(aVoid -> {
            clearResults();
            BiFunction<List<Movie>, List<Post>, List<ApiResult>> zipper = (movies, posts) -> getApiResultsBound(movies, posts);
            Observable.zip(moviesObservable, postsObservable, zipper)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<ApiResult>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Timber.e("Thread: " + Thread.currentThread().getName() + ", zip observable - onSubscribe");
                        }

                        @Override
                        public void onNext(List<ApiResult> results) {
                            Timber.e("Thread: " + Thread.currentThread().getName() + ", zip observable - onNext, timestamp: " + System.currentTimeMillis());
                            adapter.addApiResults(results, false);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Timber.e("Thread: " + Thread.currentThread().getName() + ", zip observable - onError: " + e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            Timber.e("Thread: " + Thread.currentThread().getName() + ", zip observable - onComplete");
                        }
                    });

        });
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    protected ProofOfConceptViewModel(Parcel in) {

    }

    public static final Creator<ProofOfConceptViewModel> CREATOR = new Creator<ProofOfConceptViewModel>() {
        @Override
        public ProofOfConceptViewModel createFromParcel(Parcel source) {
            return new ProofOfConceptViewModel(source);
        }

        @Override
        public ProofOfConceptViewModel[] newArray(int size) {
            return new ProofOfConceptViewModel[size];
        }
    };
}