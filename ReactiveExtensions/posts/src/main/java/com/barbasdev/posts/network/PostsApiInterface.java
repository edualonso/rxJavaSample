package com.barbasdev.posts.network;

import com.barbasdev.posts.datamodel.Post;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by edu on 20/11/2016.
 */

public interface PostsApiInterface {
    @GET("posts")
    List<Post> getUserPosts(@Query("userId") int userId);

    @GET("posts")
    Observable<List<Post>> getUserPostsObservable(@Query("userId") int userId);
}
