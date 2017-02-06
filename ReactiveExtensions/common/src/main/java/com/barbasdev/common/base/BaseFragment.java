package com.barbasdev.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by edu on 20/11/2016.
 */

public abstract class BaseFragment<V extends BaseViewModel> extends Fragment {

    protected V viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getArguments().getParcelable(BaseActivity.ARG_VIEWMODEL);
    }

}
