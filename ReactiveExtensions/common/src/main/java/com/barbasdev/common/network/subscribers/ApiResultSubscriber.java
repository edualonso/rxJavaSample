package com.barbasdev.common.network.subscribers;

import com.barbasdev.common.network.subscribers.callbacks.SubscriberCallback;

import rx.Subscriber;

/**
 * Created by edu on 23/11/2016.
 */
public abstract class ApiResultSubscriber<T> extends Subscriber<T> {

    protected SubscriberCallback subscriberCallback;

    public ApiResultSubscriber(SubscriberCallback subscriberCallback) {
        this.subscriberCallback = subscriberCallback;
    }
}
