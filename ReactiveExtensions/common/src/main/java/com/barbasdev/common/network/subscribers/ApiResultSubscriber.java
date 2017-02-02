package com.barbasdev.common.network.subscribers;

import com.barbasdev.common.network.subscribers.callbacks.SubscriberCallback;

import rx.Observer;
import timber.log.Timber;

/**
 * Created by edu on 23/11/2016.
 */
public abstract class ApiResultSubscriber<T> implements Observer<T> {

    protected SubscriberCallback subscriberCallback;

    public ApiResultSubscriber(SubscriberCallback subscriberCallback) {
        this.subscriberCallback = subscriberCallback;
    }

    @Override
    public void onCompleted() {
        // override in children classes
        Timber.e("----------> onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        // override in children classes
        Timber.e("----------> onError");
    }

    @Override
    public void onNext(T result) {
        // override in children classes
    }
}
