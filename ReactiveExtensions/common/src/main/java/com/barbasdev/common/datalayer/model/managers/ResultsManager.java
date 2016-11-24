package com.barbasdev.common.datalayer.model.managers;

import com.barbasdev.common.network.subscribers.ApiResultSubscriber;

/**
 * Created by edu on 23/11/2016.
 */

public interface ResultsManager<T, S extends ApiResultSubscriber<T>> {
    void getResults(S subscriber);
}
