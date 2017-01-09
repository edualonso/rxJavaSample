package com.barbasdev.tools.rxbinding;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.view.View;

import com.barbasdev.common.base.BaseViewModel;
import com.barbasdev.common.network.subscribers.callbacks.SubscriberCallback;
import com.barbasdev.movies.datamodel.Movie;
import com.barbasdev.movies.datamodel.MovieResults;
import com.barbasdev.posts.datamodel.Post;
import com.barbasdev.tools.BR;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import timber.log.Timber;

/**
 * Created by edu on 09/01/2017.
 */

public class RxBindingViewModel extends BaseViewModel<RxBindingFragment> implements SubscriberCallback<List<String>> {

    private String text;

    public RxBindingViewModel(Activity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
    }

    public View.OnClickListener getOnClickListener() {
        return view -> {
//            Func2<MovieResults, List<Post>, List<String>> zipFunction = (movieResults, postResults) -> getMoviesAndPosts(movieResults, postResults);
//            Observable.zip(MoviesManager.getInstance().getResults(), PostsManager.getInstance().getResults(), zipFunction)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new RxBindingSubscriber(this));

//            Observable.range(1,10)
//                    .flatMap(new Func1<Integer, Observable<Integer>>() {
//                        @Override
//                        public Observable<Integer> call(Integer v) {
//                            return Observable.range(v, 2);
//                        }
//                    })
//                    .subscribe(new Action1<Integer>() {
//                        @Override
//                        public void call(Integer value) {
//                            Timber.e("Value: " + value);
//                        }
//                    });

            Observable.range(1, 10)
                    .map(new Func1<Integer, Integer>() {
                        @Override
                        public Integer call(Integer value) {
                            return value * value;
                        }
                    })
                    .flatMap(new Func1<Integer, Observable<Integer>>() {
                        @Override
                        public Observable<Integer> call(Integer value) {
                            return Observable.range(value, 2);
                        }
                    })
                    .subscribe(new Action1<Integer>() {
                        @Override
                        public void call(Integer value) {
                            Timber.e("Value: " + value);
                        }
                    });
        };
    }

    @Override
    public void processResult(List<String> result) {
        for (String title : result) {
            Timber.e("Title: " + title);
        }
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
