package com.barbasdev.tools.rxbinding;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Parcel;
import android.support.annotation.NonNull;

import com.barbasdev.common.base.BaseViewModel;
import com.barbasdev.common.network.subscribers.callbacks.SubscriberCallback;
import com.barbasdev.movies.datamodel.Movie;
import com.barbasdev.movies.datamodel.managers.MoviesManager;
import com.barbasdev.posts.datamodel.Post;
import com.barbasdev.posts.datamodel.managers.PostsManager;
import com.barbasdev.tools.BR;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by edu on 09/01/2017.
 */

public class RxBindingViewModel extends BaseViewModel implements SubscriberCallback<List<String>> {

    private String text;

    public RxBindingViewModel(Activity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
    }

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
    }

    @Override
    public void processResult(List<String> result) {
        for (String title : result) {
            Timber.e("Title: " + title);
        }
    }

    public Observable<List<String>> getResultsObservable() {
        Observable<List<Movie>> movieResultsObservable = MoviesManager.getInstance().getResults();
        Observable<List<Post>> postResultsObservable = PostsManager.getInstance().getResults();
        Func2<List<Movie>, List<Post>, List<String>> combineLambda = (movieResults, postResults) -> getMoviesAndPosts(movieResults, postResults);

        return Observable.zip(movieResultsObservable, postResultsObservable, combineLambda)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    private List<String> getMoviesAndPosts(List<Movie> movieResults, List<Post> postResults) {
        List<String> moviesAndPosts = new ArrayList<>();
        for (Movie movie : movieResults) {
            moviesAndPosts.add(movie.getTitle());
        }
        moviesAndPosts.add("*** -------------------- *** -------------------- ***");
        for (Post post : postResults) {
            moviesAndPosts.add(post.getTitle());
        }
        return moviesAndPosts;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public RxBindingViewModel() {
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
