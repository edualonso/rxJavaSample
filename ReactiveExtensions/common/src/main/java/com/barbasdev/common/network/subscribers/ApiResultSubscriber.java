package com.barbasdev.common.network.subscribers;

import com.barbasdev.common.network.subscribers.callbacks.SubscriberCallback;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
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
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {
        // override in children classes
        Timber.d("----------> onComplete");
    }

    @Override
    public void onError(Throwable e) {
        // override in children classes
        Timber.e("----------> onError: " + e.getMessage());
    }

    @Override
    public void onNext(T result) {
        // override in children classes
    }
}
