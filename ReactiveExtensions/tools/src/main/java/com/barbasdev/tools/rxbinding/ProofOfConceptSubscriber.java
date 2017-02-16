package com.barbasdev.tools.rxbinding;

import com.barbasdev.common.datalayer.model.ApiResult;
import com.barbasdev.common.network.subscribers.ApiResultSubscriber;
import com.barbasdev.common.network.subscribers.callbacks.SubscriberCallback;

import java.util.List;

import timber.log.Timber;

public class ProofOfConceptSubscriber<T extends ApiResult> extends ApiResultSubscriber<List<T>> {

    public ProofOfConceptSubscriber(SubscriberCallback subscriberCallback) {
        super(subscriberCallback);
    }

    @Override
    public void onNext(List<T> result) {
        Timber.d("timestamp: " + System.currentTimeMillis());
        subscriberCallback.processResults(result);
    }
}