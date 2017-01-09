package com.barbasdev.movies.network.subscribers;

import com.barbasdev.common.network.subscribers.ApiResultSubscriber;
import com.barbasdev.common.network.subscribers.callbacks.SubscriberCallback;
import com.barbasdev.movies.datamodel.MovieResults;

import timber.log.Timber;

/**
 * Created by edu on 20/11/2016.
 */
public class MovieResultsSubscriber extends ApiResultSubscriber<MovieResults> {

    public MovieResultsSubscriber(SubscriberCallback subscriberCallback) {
        super(subscriberCallback);
    }

    @Override
    public void onNext(MovieResults results) {
        Timber.d("----------> onNext");
        subscriberCallback.processResult(results);
    }
}