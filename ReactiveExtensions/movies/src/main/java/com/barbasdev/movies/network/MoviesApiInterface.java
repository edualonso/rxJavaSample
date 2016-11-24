package com.barbasdev.movies.network;

import com.barbasdev.common.network.BaseApiInterface;
import com.barbasdev.movies.datamodel.MovieResults;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by edu on 20/11/2016.
 */

public interface MoviesApiInterface extends BaseApiInterface {
    @GET("movie/top_rated")
    Observable<MovieResults> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Observable<MovieResults> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
