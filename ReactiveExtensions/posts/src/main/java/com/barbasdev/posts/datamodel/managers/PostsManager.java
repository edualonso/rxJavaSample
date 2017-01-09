package com.barbasdev.posts.datamodel.managers;

import com.barbasdev.common.datalayer.model.managers.ResultsManager;
import com.barbasdev.posts.datamodel.Post;
import com.barbasdev.posts.network.PostsApiClient;
import com.barbasdev.posts.network.subscribers.PostResultsSubscriber;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by edu on 23/11/2016.
 */
public class PostsManager implements ResultsManager<List<Post>, PostResultsSubscriber> {

    private static PostsManager instance;

    public static PostsManager getInstance() {
        if (instance == null) {
            instance = new PostsManager();
        }
        return instance;
    }

    @Override
    public void getResults(PostResultsSubscriber postsSubscriber) {
        Observable<List<Post>> call = PostsApiClient.getInstance().getService().getUserPosts(1);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(postsSubscriber);
    }

    @Override
    public Observable<List<Post>> getResults() {
        return PostsApiClient.getInstance().getService().getUserPosts(1);
    }
}
