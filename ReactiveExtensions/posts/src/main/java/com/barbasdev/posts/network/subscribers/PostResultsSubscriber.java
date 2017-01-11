package com.barbasdev.posts.network.subscribers;

import android.util.Log;

import com.barbasdev.common.network.subscribers.ApiResultSubscriber;
import com.barbasdev.common.network.subscribers.callbacks.SubscriberCallback;
import com.barbasdev.posts.datamodel.Post;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by edu on 20/11/2016.
 */
public class PostResultsSubscriber extends ApiResultSubscriber<List<Post>> {

    private static final String TAG = PostResultsSubscriber.class.getSimpleName();

    public PostResultsSubscriber(SubscriberCallback subscriberCallback) {
        super(subscriberCallback);
    }

    @Override
    public void onSubscribe(Disposable d) {
        // do as needed
    }

    @Override
    public void onNext(List<Post> postList) {
        Log.d(TAG, "----------> onNext");
        subscriberCallback.processResult(postList);
    }

}