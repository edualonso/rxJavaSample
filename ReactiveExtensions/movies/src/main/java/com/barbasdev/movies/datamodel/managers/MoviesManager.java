package com.barbasdev.movies.datamodel.managers;

import com.barbasdev.common.datalayer.model.managers.ResultsManager;
import com.barbasdev.movies.datamodel.MovieResults;
import com.barbasdev.movies.network.MoviesApiClient;
import com.barbasdev.movies.network.subscribers.MovieResultsSubscriber;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by edu on 23/11/2016.
 */
public class MoviesManager implements ResultsManager<MovieResults, MovieResultsSubscriber> {

    private static MoviesManager instance;

    public static MoviesManager getInstance() {
        if (instance == null) {
            instance = new MoviesManager();
        }
        return instance;
    }

    @Override
    public void getResults(MovieResultsSubscriber movieResultsSubscriber) {
        Observable<MovieResults> call = MoviesApiClient.getInstance().getService().getTopRatedMovies(MoviesApiClient.API_KEY);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieResultsSubscriber);
    }
}
