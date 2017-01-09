package com.barbasdev.posts;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Parcel;
import android.view.View;

import com.barbasdev.common.base.BaseViewModel;
import com.barbasdev.common.network.subscribers.callbacks.SubscriberCallback;
import com.barbasdev.posts.datamodel.Post;
import com.barbasdev.posts.datamodel.managers.PostsManager;
import com.barbasdev.posts.network.subscribers.PostResultsSubscriber;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by edu on 20/11/2016.
 */

public class PostsViewModel extends BaseViewModel implements SubscriberCallback<List<Post>> {

    private String text;

    public PostsViewModel(Activity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
    }

    public View.OnClickListener getOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostsManager.getInstance().getResults(new PostResultsSubscriber(PostsViewModel.this));
            }
        };
    }

    @Override
    public void processResult(List<Post> postList) {
        setText("NUMBER OF POSTS: " + postList.size());
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
