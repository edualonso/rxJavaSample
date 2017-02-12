//package com.barbasdev.reactiveextensions.main;
//
//import android.databinding.Bindable;
//import android.os.Parcel;
//
//import com.barbasdev.common.base.BaseViewModel;
//import com.barbasdev.reactiveextensions.BR;
//
///**
// * Created by edu on 11/02/2017.
// */
//
//public class MainViewModel extends BaseViewModel {
//
//    private String moviesButtonText;
//    private String postsButtonText;
//    private String testButtonText;
//
//    public MainViewModel() {
//
//    }
//
//    @Bindable
//    public String getMoviesButtonText() {
//        return moviesButtonText;
//    }
//
//    public void setMoviesButtonText(String moviesButtonText) {
//        this.moviesButtonText = moviesButtonText;
//        notifyPropertyChanged(BR.moviesButtonText);
//    }
//
//    @Bindable
//    public String getPostsButtonText() {
//        return postsButtonText;
//    }
//
//    public void setPostsButtonText(String postsButtonText) {
//        this.postsButtonText = postsButtonText;
//        notifyPropertyChanged(BR.postsButtonText);
//    }
//
//    @Bindable
//    public String getTestButtonText() {
//        return testButtonText;
//    }
//
//    public void setTestButtonText(String testButtonText) {
//        this.testButtonText = testButtonText;
//        notifyPropertyChanged(BR.testButtonText);
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//    }
//
//
//    protected MainViewModel(Parcel in) {
//    }
//
//    public static final Creator<MainViewModel> CREATOR = new Creator<MainViewModel>() {
//        @Override
//        public MainViewModel createFromParcel(Parcel source) {
//            return new MainViewModel(source);
//        }
//
//        @Override
//        public MainViewModel[] newArray(int size) {
//            return new MainViewModel[size];
//        }
//    };
//}