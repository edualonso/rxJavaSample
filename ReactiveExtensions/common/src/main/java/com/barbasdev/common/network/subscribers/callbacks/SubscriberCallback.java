package com.barbasdev.common.network.subscribers.callbacks;

public interface SubscriberCallback<T> {
    void processResult(T result);
}