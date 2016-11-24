package com.barbasdev.posts.network;

import com.barbasdev.common.network.BaseApiClient;

/**
 * Created by edu on 20/11/2016.
 */

public class PostsApiClient extends BaseApiClient<PostsApiInterface> {

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private static PostsApiClient instance;

    public static PostsApiClient getInstance() {
        if (instance == null) {
            instance = new PostsApiClient(BASE_URL);
        }
        return instance;
    }

    private PostsApiClient(String url) {
        super(url);
    }

    @Override
    public PostsApiInterface getService() {
        return getClient().create(PostsApiInterface.class);
    }

}
