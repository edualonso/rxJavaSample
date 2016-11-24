package com.barbasdev.common.datalayer.persistence;

import com.barbasdev.common.datalayer.model.ApiResult;

/**
 * Created by edu on 23/11/2016.
 */

public interface PersistenceManager<T extends ApiResult> {
    void saveResults(T results);
    T getAll();
}
