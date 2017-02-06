package com.barbasdev.movies.network.subscribers;

import com.barbasdev.common.network.subscribers.ApiResultSubscriber;
import com.barbasdev.common.network.subscribers.callbacks.SubscriberCallback;
import com.barbasdev.movies.datamodel.Movie;

import java.util.List;

import timber.log.Timber;

/**
 * Created by edu on 20/11/2016.
 */
public class MovieResultsSubscriber extends ApiResultSubscriber<List<Movie>> {

    public MovieResultsSubscriber(SubscriberCallback subscriberCallback) {
        super(subscriberCallback);
    }

    @Override
    public void onNext(List<Movie> results) {
        Timber.d("----------> onNext");
        subscriberCallback.processResults(results);
    }
}