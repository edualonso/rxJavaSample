package com.barbasdev.common.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by edu on 20/11/2016.
 */

public abstract class BaseFragment<B extends ViewDataBinding, V extends BaseViewModel> extends Fragment implements BaseFragmentView {

    protected B binding;
    protected V viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getArguments().getParcelable(BaseActivity.ARG_VIEWMODEL);
        if (viewModel != null) {
            viewModel.setFragmentView(this);
        }
    }

    @Nullable
    public View getView(@IdRes int resId) {
        View view = getView();
        if (view != null) {
            return view.findViewById(resId);
        }
        return null;
    }
}
