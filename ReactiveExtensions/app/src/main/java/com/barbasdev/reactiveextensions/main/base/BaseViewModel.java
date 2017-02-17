package com.barbasdev.reactiveextensions.main.base;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.os.Parcelable;

import java.lang.ref.WeakReference;

/**
 * Created by edu on 20/11/2016.
 */

public abstract class BaseViewModel extends BaseObservable implements Parcelable {

    private WeakReference<Activity> activityWeakReference = new WeakReference<>(null);

    /**
     * Use to initialise things such as data structures, adapters, or injects the viewmodel to the dependency graph.
     * This method should be called from {@link BaseFragment#setupViewModel()}.
     */
    public abstract void setup();

    public void onCreate() {}
    public void onStart() {}
    public void onResume() {}
    public void onPause() {}
    public void onStop() {}
    public void onDestroy() {}

    public WeakReference<Activity> getActivityWeakReference() {
        return activityWeakReference;
    }

    public void setActivityWeakReference(WeakReference<Activity> activityWeakReference) {
        this.activityWeakReference = activityWeakReference;
    }

}