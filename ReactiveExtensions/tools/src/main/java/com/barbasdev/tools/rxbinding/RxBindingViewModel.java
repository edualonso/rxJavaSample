package com.barbasdev.tools.rxbinding;

import android.app.Activity;
import android.graphics.Color;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.barbasdev.common.base.BaseViewModel;
import com.barbasdev.common.datalayer.model.ApiResult;
import com.barbasdev.common.network.subscribers.ApiResultSubscriber;
import com.barbasdev.common.network.subscribers.callbacks.SubscriberCallback;
import com.barbasdev.movies.datamodel.Movie;
import com.barbasdev.movies.datamodel.managers.MoviesManager;
import com.barbasdev.posts.datamodel.Post;
import com.barbasdev.posts.datamodel.managers.PostsManager;

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

public class RxBindingViewModel<T extends ApiResult> extends BaseViewModel implements SubscriberCallback<List<T>> {

    private List<T> apiResults = new ArrayList<>();
    private RxFragmentResultsAdapter adapter;

    public RxBindingViewModel(Activity activity) {
        activityWeakReference = new WeakReference<>(activity);
        adapter = new RxFragmentResultsAdapter();
    }

    @Override
    public void processResults(List<T> results) {
        apiResults.addAll(results);
        adapter.notifyDataSetChanged();
    }

    public Observable<List<String>> getResultsObservable() {
        Observable<List<Movie>> movieResultsObservable = MoviesManager.getInstance().getResults();
        Observable<List<Post>> postResultsObservable = PostsManager.getInstance().getResults();
        Func2<List<Movie>, List<Post>, List<String>> combineLambda = new Func2<List<Movie>, List<Post>, List<String>>() {
            @Override
            public List<String> call(List<Movie> movieResults, List<Post> postResults) {
                return getMoviesAndPosts(movieResults, postResults);
            }
        };

        return Observable.zip(movieResultsObservable, postResultsObservable, combineLambda)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    public List<String> getMoviesAndPosts(List<Movie> movieResults, List<Post> postResults) {
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

    @NonNull
    public List<T> getApiResults(List<T> movieResults, List<T> postResults) {
        List<T> results = new ArrayList<>();
        results.addAll(movieResults);
        results.addAll(postResults);
        return results;
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

    public RxFragmentSubscriber getApiResultsSubscriber() {
        return new RxFragmentSubscriber(this);
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }








    public static class ResultViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ResultViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }

    public class RxFragmentResultsAdapter extends RecyclerView.Adapter<ResultViewHolder> {
        @Override
        public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(parent.getContext());
            textView.setTextColor(Color.BLACK);
            return new ResultViewHolder(textView);
        }

        @Override
        public void onBindViewHolder(ResultViewHolder holder, int position) {
            holder.textView.setText(apiResults.get(position).getTitle());
        }

        @Override
        public int getItemCount() {
            return apiResults.size();
        }
    }

    public class RxFragmentSubscriber extends ApiResultSubscriber<List<T>> {

        public RxFragmentSubscriber(SubscriberCallback subscriberCallback) {
            super(subscriberCallback);
        }

        @Override
        public void onNext(List<T> result) {
            Timber.d("timestamp: " + System.currentTimeMillis());
            subscriberCallback.processResults(result);
        }
    }
}
