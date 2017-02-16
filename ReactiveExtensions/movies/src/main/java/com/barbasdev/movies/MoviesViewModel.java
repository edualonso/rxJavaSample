package com.barbasdev.movies;

import android.os.Parcel;

import com.barbasdev.common.base.BaseViewModel;
import com.barbasdev.common.datalayer.model.ApiResultAdapter;
import com.barbasdev.movies.datamodel.Movie;
import com.barbasdev.movies.datamodel.managers.MoviesManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by edu on 20/11/2016.
 */

public class MoviesViewModel extends BaseViewModel {

    private ApiResultAdapter adapter;

    public MoviesViewModel() {
        adapter = new ApiResultAdapter();
    }

    public ApiResultAdapter<Movie> getAdapter() {
        if (adapter.getApiResults() == null) {
            adapter.initApiResults();
        }
        return adapter;
    }

    @Override
    public void setup() {
        adapter = new ApiResultAdapter();
    }

    public void queryMovie(Observable<CharSequence> charSequenceObservable) {
        Predicate<CharSequence> charSequencePredicate = charSequence -> {
            Timber.e("Thread: " + Thread.currentThread().getName() + ", FILTERING RESULTS (movies), length: " + charSequence.length());
            if (charSequence.length() < 3) {
                adapter.clearApiResults();
                return false;
            }
            return true;
        };

        Function<CharSequence, ObservableSource<List<Movie>>> switchMapper = new Function<CharSequence, ObservableSource<List<Movie>>>() {
            @Override
            public ObservableSource<List<Movie>> apply(CharSequence charSequence) throws Exception {
                Timber.e("Thread: " + Thread.currentThread().getName() + ", PREPARING QUERY (movies): " + charSequence);
                return MoviesManager.getInstance().getMovieDetailsObservable(charSequence.toString());
            }
        };

        Observer<List<Movie>> observer = new Observer<List<Movie>>() {
            @Override
            public void onComplete() {
                Timber.e("-----------------------> onCompleted (movies)");
            }

            @Override
            public void onError(Throwable e) {
                Timber.e("-----------------------> onError (movies): " + e.getMessage());
            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Movie> movies) {
                for (Movie movie : movies) {
                    Timber.e("Thread: " + Thread.currentThread().getName() + ", Movie: " + movie.getTitle());
                }

                adapter.addApiResults(movies, true);
            }
        };

        charSequenceObservable
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(charSequencePredicate)
                .debounce(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .switchMapDelayError(switchMapper)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    protected MoviesViewModel(Parcel in) {

    }

    public static final Creator<MoviesViewModel> CREATOR = new Creator<MoviesViewModel>() {
        @Override
        public MoviesViewModel createFromParcel(Parcel source) {
            return new MoviesViewModel(source);
        }

        @Override
        public MoviesViewModel[] newArray(int size) {
            return new MoviesViewModel[size];
        }
    };
}