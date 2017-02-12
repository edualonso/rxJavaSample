package com.barbasdev.movies;

import android.os.Parcel;

import com.barbasdev.common.base.BaseViewModel;
import com.barbasdev.common.datalayer.model.ApiResultAdapter;
import com.barbasdev.common.network.subscribers.callbacks.SubscriberCallback;
import com.barbasdev.movies.datamodel.Movie;
import com.barbasdev.movies.datamodel.MovieResults;

/**
 * Created by edu on 20/11/2016.
 */

public class MoviesViewModel extends BaseViewModel implements SubscriberCallback<MovieResults> {

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
    public void processResults(MovieResults movieResults) {
//        setText("NUMBER OF MOVIES: " + movieResults.getResults().size());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.adapter, flags);
    }

    protected MoviesViewModel(Parcel in) {
        this.adapter = in.readParcelable(ApiResultAdapter.class.getClassLoader());
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