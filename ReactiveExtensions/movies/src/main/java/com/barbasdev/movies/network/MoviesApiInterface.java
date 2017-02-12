package com.barbasdev.movies.network;

import com.barbasdev.movies.datamodel.MovieResults;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by edu on 20/11/2016.
 */

public interface MoviesApiInterface {
    @GET("movie/top_rated")
    MovieResults getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Observable<MovieResults> getTopRatedMoviesObservable(@Query("api_key") String apiKey);

//    @GET("movie/{id}")
//    MovieResults getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
//
//    @GET("movie/{id}")
//    Observable<MovieResults> getMovieDetailsObservable(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("search/movie")
    MovieResults getMovieDetails(@Query("api_key") String apiKey, @Query("query") String query);

    @GET("search/movie")
    Observable<MovieResults> getMovieDetailsObservable(@Query("api_key") String apiKey, @Query("query") String query);
}
