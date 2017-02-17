//package com.barbasdev.common.base;
//
//import android.databinding.ViewDataBinding;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//
///**
// * Created by edu on 20/11/2016.
// */
//
//public abstract class BaseFragment<V extends BaseViewModel, B extends ViewDataBinding> extends Fragment {
//
//    protected B binding;
//    protected V viewModel;
//
//    /**
//     * Use to initialise anything your viewmodel needs after it has been unmarshalled.
//     * Instantiate your adapters or data structures that are not added to the parcel here.
//     */
//    protected abstract void setupViewModel();
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        viewModel = getArguments().getParcelable(BaseActivity.ARG_VIEWMODEL);
//        setupViewModel();
//    }
//}