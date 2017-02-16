package com.barbasdev.movies.datamodel.managers;

import com.barbasdev.common.datalayer.model.managers.ResultsManager;
import com.barbasdev.movies.datamodel.Movie;
import com.barbasdev.movies.datamodel.MovieResults;
import com.barbasdev.movies.network.MoviesApiClient;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import timber.log.Timber;

/**
 * Created by edu on 23/11/2016.
 */
public class MoviesManager implements ResultsManager<List<Movie>> {

    private static MoviesManager instance;

    public static MoviesManager getInstance() {
        if (instance == null) {
            instance = new MoviesManager();
        }
        return instance;
    }

    /**
     *
     * @return
     */
    public Observable<List<Movie>> getTopRatedMovies() {
        Observable<MovieResults> topRatedMoviesObservable = MoviesApiClient.getInstance().getService().getTopRatedMoviesObservable(MoviesApiClient.API_KEY);
        return getMovieListObservable(topRatedMoviesObservable);
    }

    /**
     *
     * @param query
     * @return
     */
    public Observable<List<Movie>> getMovieDetailsObservable(String query) {
        Observable<MovieResults> movieDetailsObservable = MoviesApiClient.getInstance().getService().getMovieDetailsObservable(MoviesApiClient.API_KEY, query);
        return getMovieListObservable(movieDetailsObservable);
    }

    /**
     * Converts the output from the service, that comes out as an Observable<MovieResults> to
     * Observable<List<Movie>> for convenience.
     *
     * @return right type of observable
     */
    private Observable<List<Movie>> getMovieListObservable(Observable<MovieResults> moviesObservable) {
        return moviesObservable.flatMapIterable(new Function<MovieResults, List<Movie>>() {
            @Override
            public List<Movie> apply(MovieResults movieResults) throws Exception {
                Timber.e("-----------------------> apply (movies)");
                return movieResults.getResults();
            }
        }).toList().toObservable();
    }
}