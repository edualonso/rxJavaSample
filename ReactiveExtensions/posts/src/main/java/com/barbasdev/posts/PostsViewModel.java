package com.barbasdev.posts;

import android.databinding.Bindable;
import android.os.Parcel;
import android.view.View;

import com.barbasdev.common.base.BaseViewModel;

/**
 * Created by edu on 20/11/2016.
 */

public class PostsViewModel extends BaseViewModel {

    private String text;

    public PostsViewModel() {

    }

    @Override
    public void setup() {
        // do nothing, not needed
    }

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(com.barbasdev.posts.BR.text);
    }

    public View.OnClickListener getOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                PostsManager.getInstance().getResults(new PostResultsSubscriber(PostsViewModel.this));
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
    }

    protected PostsViewModel(Parcel in) {
        this.text = in.readString();
    }

    public static final Creator<PostsViewModel> CREATOR = new Creator<PostsViewModel>() {
        @Override
        public PostsViewModel createFromParcel(Parcel source) {
            return new PostsViewModel(source);
        }

        @Override
        public PostsViewModel[] newArray(int size) {
            return new PostsViewModel[size];
        }
    };
}
