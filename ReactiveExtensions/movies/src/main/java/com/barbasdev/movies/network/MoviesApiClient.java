package com.barbasdev.movies.network;

import com.barbasdev.common.network.BaseApiClient;

/**
 * Created by edu on 20/11/2016.
 */

public class MoviesApiClient extends BaseApiClient<MoviesApiInterface> {

    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String API_KEY = "0de63959274df38be1139ab269854c44";

    private static MoviesApiClient instance;

    public static MoviesApiClient getInstance() {
        if (instance == null) {
            instance = new MoviesApiClient(BASE_URL);
        }
        return instance;
    }

    private MoviesApiClient(String url) {
        super(url);
    }

    public MoviesApiInterface getService() {
        return getClient().create(MoviesApiInterface.class);
    }

}
