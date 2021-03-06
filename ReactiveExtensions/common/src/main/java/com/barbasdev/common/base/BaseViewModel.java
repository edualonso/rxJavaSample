package com.barbasdev.common.base;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.os.Parcelable;

import java.lang.ref.WeakReference;

/**
 * Created by edu on 20/11/2016.
 */

public abstract class BaseViewModel<V extends BaseFragmentView> extends BaseObservable implements Parcelable {

    protected WeakReference<Activity> activityWeakReference = new WeakReference<>(null);
    protected V fragmentView;

    public void onCreate() {}
    public void onStart() {}
    public void onResume() {}
    public void onPause() {}
    public void onStop() {}
    public void onDestroy() {}

    public void setFragmentView(V fragmentView) {
        this.fragmentView = fragmentView;
    }
}
