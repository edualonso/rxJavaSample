package com.barbasdev.common.network;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by edu on 23/11/2016.
 */
public abstract class BaseApiClient<T> {

    protected final String url;

    protected Retrofit retrofit;

    public BaseApiClient(String url) {
        this.url = url;
    }

    public abstract T getService();

    protected Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build();
        }
        return retrofit;
    }
}
