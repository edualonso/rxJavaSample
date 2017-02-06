package com.barbasdev.movies;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Parcel;
import android.view.View;

import com.barbasdev.common.base.BaseViewModel;
import com.barbasdev.common.network.subscribers.callbacks.SubscriberCallback;
import com.barbasdev.movies.datamodel.MovieResults;
import com.barbasdev.movies.datamodel.managers.MoviesManager;
import com.barbasdev.movies.network.subscribers.MovieResultsSubscriber;

import java.lang.ref.WeakReference;

/**
 * Created by edu on 20/11/2016.
 */

public class MoviesViewModel extends BaseViewModel implements SubscriberCallback<MovieResults> {

    private String text;

    public MoviesViewModel(Activity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
    }

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(com.barbasdev.movies.BR.text);
    }

    public View.OnClickListener getOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MoviesManager.getInstance().getResults(new MovieResultsSubscriber(MoviesViewModel.this));
            }
        };
    }

    @Override
    public void processResults(MovieResults movieResults) {
        setText("NUMBER OF MOVIES: " + movieResults.getResults().size());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
    }

    protected MoviesViewModel(Parcel in) {
        this.text = in.readString();
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
