package com.barbasdev.tools.rxbinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barbasdev.common.base.BaseFragment;
import com.barbasdev.tools.R;
import com.jakewharton.rxbinding.view.RxView;

/**
 * Created by edu on 09/01/2017.
 */

public class RxBindingFragment extends BaseFragment<ViewDataBinding, RxBindingViewModel> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rxbinding, container, false);

        viewModel.setText("RXBINDING");
        binding.setVariable(com.barbasdev.tools.BR.viewModel, viewModel);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RxView.clicks(view.findViewById(R.id.rxBindingButton))
                .subscribe(aVoid -> viewModel.getResultsObservable()
                        .subscribe(new RxBindingSubscriber(viewModel))
                );
    }
}
