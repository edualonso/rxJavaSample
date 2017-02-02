package com.barbasdev.posts.network.subscribers;

import android.util.Log;

import com.barbasdev.common.network.subscribers.ApiResultSubscriber;
import com.barbasdev.common.network.subscribers.callbacks.SubscriberCallback;
import com.barbasdev.posts.datamodel.Post;

import java.util.List;

/**
 * Created by edu on 20/11/2016.
 */
public class PostResultsSubscriber extends ApiResultSubscriber<List<Post>> {

    private static final String TAG = PostResultsSubscriber.class.getSimpleName();

    public PostResultsSubscriber(SubscriberCallback subscriberCallback) {
        super(subscriberCallback);
    }

    @Override
    public void onNext(List<Post> postList) {
        Log.d(TAG, "----------> onNext");
        subscriberCallback.processResult(postList);
    }

}