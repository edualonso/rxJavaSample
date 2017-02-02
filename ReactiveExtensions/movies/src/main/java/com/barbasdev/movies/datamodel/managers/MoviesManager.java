package com.barbasdev.movies.datamodel.managers;

import com.barbasdev.common.datalayer.model.managers.ResultsManager;
import com.barbasdev.movies.datamodel.Movie;
import com.barbasdev.movies.datamodel.MovieResults;
import com.barbasdev.movies.network.MoviesApiClient;
import com.barbasdev.movies.network.subscribers.MovieResultsSubscriber;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by edu on 23/11/2016.
 */
public class MoviesManager implements ResultsManager<List<Movie>, MovieResultsSubscriber> {

    private static MoviesManager instance;

    public static MoviesManager getInstance() {
        if (instance == null) {
            instance = new MoviesManager();
        }
        return instance;
    }

    @Override
    public void getResults(MovieResultsSubscriber movieResultsSubscriber) {
        getMovieListObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public Observable<List<Movie>> getResults() {
        return getMovieListObservable();
    }

    /**
     * Converts the output from the service, that comes out as an Observable<MovieResults> to
     * Observable<List<Movie>> for convenience.
     *
     * @return right type of observable
     */
    private Observable<List<Movie>> getMovieListObservable() {
        return MoviesApiClient.getInstance().getService().getTopRatedMoviesObservable(MoviesApiClient.API_KEY)
                .flatMapIterable(new Func1<MovieResults, List<Movie>>() {
                    @Override
                    public List<Movie> call(MovieResults movieResults) {
                        return movieResults.getResults();
                    }
                })
                .toList();
    }
}