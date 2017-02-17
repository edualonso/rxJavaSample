package com.barbasdev.reactiveextensions.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barbasdev.reactiveextensions.R;
import com.barbasdev.reactiveextensions.databinding.FragmentMainBinding;
import com.barbasdev.reactiveextensions.main.base.BaseActivity;
import com.barbasdev.reactiveextensions.main.base.BaseApplication;
import com.barbasdev.reactiveextensions.main.base.BaseFragment;
import com.barbasdev.reactiveextensions.main.base.BaseViewModel;
import com.barbasdev.reactiveextensions.main.di.HelloService;
import com.barbasdev.reactiveextensions.main.movies.MoviesActivity;
import com.barbasdev.reactiveextensions.main.movies.MoviesViewModel;
import com.barbasdev.reactiveextensions.main.proofofconcept.ProofOfConceptActivity;
import com.barbasdev.reactiveextensions.main.proofofconcept.ProofOfConceptViewModel;
import com.jakewharton.rxbinding2.view.RxView;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by edu on 11/02/2017.
 */

public class MainFragment extends BaseFragment<BaseViewModel, FragmentMainBinding> {

    @Inject
    HelloService helloService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);

        Timber.e(helloService.greet("MainFragment: LOLOLOLO"));

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RxView.clicks(binding.moviesButton)
                .subscribe(nothing -> {
                    Intent intent = new Intent(getContext(), MoviesActivity.class);
                    intent.putExtra(BaseActivity.ARG_VIEWMODEL, new MoviesViewModel());
                    startActivity(intent);
                });

        RxView.clicks(binding.testsButton)
                .subscribe(nothing -> {
                    Intent intent = new Intent(getContext(), ProofOfConceptActivity.class);
                    intent.putExtra(BaseActivity.ARG_VIEWMODEL, new ProofOfConceptViewModel<>());
                    startActivity(intent);
                });
    }

    @Override
    protected void setupViewModel() {
        // not needed, do nothing
    }

    @Override
    public void performDependencyInjection() {
        BaseApplication.getGraph().inject(this);
    }
}
