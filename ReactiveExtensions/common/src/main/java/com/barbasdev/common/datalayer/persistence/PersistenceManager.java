package com.barbasdev.common.datalayer.persistence;

/**
 * Created by edu on 23/11/2016.
 */

public interface PersistenceManager<T> {
    void saveResult(T result);
    T getAll();
}
