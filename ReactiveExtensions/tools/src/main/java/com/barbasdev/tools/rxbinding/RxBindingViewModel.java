package com.barbasdev.tools.rxbinding;

import android.os.Parcel;
import android.support.annotation.NonNull;

import com.barbasdev.common.base.BaseViewModel;
import com.barbasdev.common.datalayer.model.ApiResult;
import com.barbasdev.common.datalayer.model.ApiResultAdapter;
import com.barbasdev.common.network.subscribers.callbacks.SubscriberCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edu on 09/01/2017.
 */

public class RxBindingViewModel<T extends ApiResult> extends BaseViewModel implements SubscriberCallback<List<T>> {

    private ApiResultAdapter adapter;

    public RxBindingViewModel() {
        adapter = new ApiResultAdapter();
    }

    @Override
    public void processResults(List<T> results) {
        adapter.addApiResults(results, false);
    }

//    public Observable<List<String>> getResultsObservable() {
//        Observable<List<Movie>> movieResultsObservable = MoviesManager.getInstance().getResults();
//        Observable<List<Post>> postResultsObservable = PostsManager.getInstance().getResults();
//        Func2<List<Movie>, List<Post>, List<String>> combineLambda = new Func2<List<Movie>, List<Post>, List<String>>() {
//            @Override
//            public List<String> call(List<Movie> movieResults, List<Post> postResults) {
//                return getMoviesAndPosts(movieResults, postResults);
//            }
//        };
//
//        return Observable.zip(movieResultsObservable, postResultsObservable, combineLambda)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }

//    @NonNull
//    public List<String> getMoviesAndPosts(List<Movie> movieResults, List<Post> postResults) {
//        List<String> moviesAndPosts = new ArrayList<>();
//        for (Movie movie : movieResults) {
//            moviesAndPosts.add(movie.getTitle());
//        }
//        moviesAndPosts.add("*** -------------------- *** -------------------- ***");
//        for (Post post : postResults) {
//            moviesAndPosts.add(post.getTitle());
//        }
//        return moviesAndPosts;
//    }

    @NonNull
    public List<T> getApiResults(List<T> movieResults, List<T> postResults) {
        List<T> results = new ArrayList<>();
        results.addAll(movieResults);
        results.addAll(postResults);
        return results;
    }

    public void clearResults() {
        adapter.clearApiResults();
    }

    public RxFragmentSubscriber getApiResultsSubscriber() {
        return new RxFragmentSubscriber(this);
    }

    public ApiResultAdapter<T> getAdapter() {
        return adapter;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    protected RxBindingViewModel(Parcel in) {
    }

    public static final Creator<RxBindingViewModel> CREATOR = new Creator<RxBindingViewModel>() {
        @Override
        public RxBindingViewModel createFromParcel(Parcel source) {
            return new RxBindingViewModel(source);
        }

        @Override
        public RxBindingViewModel[] newArray(int size) {
            return new RxBindingViewModel[size];
        }
    };
}
