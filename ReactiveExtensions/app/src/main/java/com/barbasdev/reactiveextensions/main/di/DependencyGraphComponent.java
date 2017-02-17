package com.barbasdev.reactiveextensions.main.di;

import android.databinding.ViewDataBinding;

import com.barbasdev.common.datalayer.model.ApiResult;
import com.barbasdev.reactiveextensions.main.MainActivity;
import com.barbasdev.reactiveextensions.main.MainFragment;
import com.barbasdev.reactiveextensions.main.base.BaseActivity;
import com.barbasdev.reactiveextensions.main.base.BaseApplication;
import com.barbasdev.reactiveextensions.main.base.BaseFragment;
import com.barbasdev.reactiveextensions.main.base.BaseViewModel;
import com.barbasdev.reactiveextensions.main.movies.MoviesActivity;
import com.barbasdev.reactiveextensions.main.movies.MoviesFragment;
import com.barbasdev.reactiveextensions.main.movies.MoviesViewModel;
import com.barbasdev.reactiveextensions.main.posts.PostsFragment;
import com.barbasdev.reactiveextensions.main.posts.PostsViewModel;
import com.barbasdev.reactiveextensions.main.proofofconcept.ProofOfConceptActivity;
import com.barbasdev.reactiveextensions.main.proofofconcept.ProofOfConceptFragment;
import com.barbasdev.reactiveextensions.main.proofofconcept.ProofOfConceptViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        MainModule.class
})
public interface DependencyGraphComponent {

    void inject(BaseActivity activity);
    void inject(MainActivity activity);
    void inject(MoviesActivity activity);
    void inject(ProofOfConceptActivity activity);

    void inject(BaseFragment<BaseViewModel, ViewDataBinding> fragment);
    void inject(MainFragment fragment);
    void inject(MoviesFragment fragment);
    void inject(ProofOfConceptFragment fragment);
    void inject(PostsFragment fragment);

    void inject(BaseViewModel viewModel);
    void inject(MoviesViewModel viewModel);
    void inject(PostsViewModel viewModel);
    void inject(ProofOfConceptViewModel<ApiResult> viewModel);

    final class Initializer {
        private Initializer() {

        }

        public static DependencyGraphComponent init(BaseApplication application) {
            return DaggerDependencyGraphComponent.builder()
                    .mainModule(new MainModule(application))
                    .build();
        }
    }
}