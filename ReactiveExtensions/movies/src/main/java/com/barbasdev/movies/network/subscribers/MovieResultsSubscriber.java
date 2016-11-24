package com.barbasdev.movies.network.subscribers;

import android.util.Log;

import com.barbasdev.common.network.subscribers.ApiResultSubscriber;
import com.barbasdev.common.network.subscribers.callbacks.SubscriberCallback;
import com.barbasdev.movies.datamodel.MovieResults;

/**
 * Created by edu on 20/11/2016.
 */
public class MovieResultsSubscriber extends ApiResultSubscriber<MovieResults> {

    private static final String TAG = MovieResultsSubscriber.class.getSimpleName();

    public MovieResultsSubscriber(SubscriberCallback subscriberCallback) {
        super(subscriberCallback);
    }

    @Override
    public void onCompleted() {
        Log.d(TAG, "----------> onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, "----------> onError");
    }

    @Override
    public void onNext(MovieResults movieResults) {
        Log.d(TAG, "----------> onNext");
        subscriberCallback.processResult(movieResults);
    }
}