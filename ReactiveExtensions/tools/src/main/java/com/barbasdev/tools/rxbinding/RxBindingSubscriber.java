package com.barbasdev.tools.rxbinding;

import com.barbasdev.common.network.subscribers.ApiResultSubscriber;
import com.barbasdev.common.network.subscribers.callbacks.SubscriberCallback;

import java.util.List;

/**
 * Created by edu on 09/01/2017.
 */
public class RxBindingSubscriber extends ApiResultSubscriber<List<String>> {

    public RxBindingSubscriber(SubscriberCallback subscriberCallback) {
        super(subscriberCallback);
    }

    @Override
    public void onNext(List<String> results) {
        subscriberCallback.processResult(results);
    }
}
