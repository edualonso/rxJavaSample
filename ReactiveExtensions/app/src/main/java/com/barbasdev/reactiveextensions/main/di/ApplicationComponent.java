package com.barbasdev.reactiveextensions.main.di;

import android.databinding.ViewDataBinding;

import com.barbasdev.common.base.BaseActivity;
import com.barbasdev.common.base.BaseComponent;
import com.barbasdev.common.base.BaseFragment;
import com.barbasdev.common.base.BaseViewModel;
import com.barbasdev.common.datalayer.model.ApiResult;
import com.barbasdev.movies.MoviesActivity;
import com.barbasdev.movies.MoviesFragment;
import com.barbasdev.movies.MoviesViewModel;
import com.barbasdev.reactiveextensions.main.MainActivity;
import com.barbasdev.reactiveextensions.main.MainFragment;
import com.barbasdev.tools.rxbinding.ProofOfConceptActivity;
import com.barbasdev.tools.rxbinding.ProofOfConceptFragment;
import com.barbasdev.tools.rxbinding.ProofOfConceptViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class
})
public interface ApplicationComponent extends BaseComponent {

    void inject(BaseActivity activity);
    void inject(MainActivity activity);
    void inject(MoviesActivity activity);
    void inject(ProofOfConceptActivity activity);

    void inject(BaseFragment<BaseViewModel, ViewDataBinding> fragment);
    void inject(MainFragment fragment);
    void inject(MoviesFragment Fragment);
    void inject(ProofOfConceptFragment fragment);

    void inject(BaseViewModel viewModel);
    void inject(MoviesViewModel viewModel);
    void inject(ProofOfConceptViewModel<ApiResult> viewModel);
}