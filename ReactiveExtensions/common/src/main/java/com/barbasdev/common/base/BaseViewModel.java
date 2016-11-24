package com.barbasdev.common.base;

import android.databinding.BaseObservable;
import android.os.Parcelable;

/**
 * Created by edu on 20/11/2016.
 */

public abstract class BaseViewModel extends BaseObservable implements Parcelable {

    public abstract void onCreate();
    public abstract void onStart();
    public abstract void onResume();
    public abstract void onPause();
    public abstract void onStop();
    public abstract void onDestroy();

}
