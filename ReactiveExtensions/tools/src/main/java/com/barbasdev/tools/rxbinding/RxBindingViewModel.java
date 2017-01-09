package com.barbasdev.tools.rxbinding;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Parcel;

import com.barbasdev.common.base.BaseViewModel;
import com.barbasdev.tools.BR;

import java.lang.ref.WeakReference;

/**
 * Created by edu on 09/01/2017.
 */

public class RxBindingViewModel extends BaseViewModel<RxBindingFragment> {

    private String text;

    public RxBindingViewModel(Activity activity) {
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

//    public View.OnClickListener getOnClickListener() {
//        fragmentView.getView(R.id.rxBindingButton);
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public RxBindingViewModel() {
    }

    protected RxBindingViewModel(Parcel in) {
    }

    public static final Creator<RxBindingViewModel> CREATOR = new Creator<RxBindingViewModel>() {
        @Override
        public RxBindingViewModel createFromParcel(Parcel source) {
            return new RxBindingViewModel(source);
        }

        @Override
        public RxBindingViewModel[] newArray(int size) {
            return new RxBindingViewModel[size];
        }
    };
}
