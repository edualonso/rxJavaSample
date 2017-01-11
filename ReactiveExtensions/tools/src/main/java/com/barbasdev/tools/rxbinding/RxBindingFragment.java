package com.barbasdev.tools.rxbinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barbasdev.common.base.BaseFragment;
import com.barbasdev.tools.R;

/**
 * Created by edu on 09/01/2017.
 */

public class RxBindingFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rxbinding, container, false);

        RxBindingViewModel viewModel = (RxBindingViewModel) super.viewModel;
        viewModel.setText("RXBINDING");
        binding.setVariable(com.barbasdev.tools.BR.viewModel, viewModel);

        return binding.getRoot();
    }

    /**
     * RxBinding doesn't work with RxJava2 at the moment, so this solution cannot be used...
     */

/*
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Observable<MovieResults> movieResultsObservable = MoviesManager.getInstance().getResults();
        Observable<List<Post>> postResultsObservable = PostsManager.getInstance().getResults();
        Func2<MovieResults, List<Post>, List<String>> zipFunction = (movieResults, postResults) -> getMoviesAndPosts(movieResults, postResults);
        Observer<List<String>> movieTitleObserver = new Observer<List<String>>() {
            @Override
            public void onCompleted() {
                Timber.e("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Timber.e("onError");
            }

            @Override
            public void onNext(List<String> titles) {
                for (String title : titles) {
                    Timber.e("Title: " + title);
                }
            }
        };

        RxView.clicks(view.findViewById(R.id.rxBindingButton))
                .subscribe(aVoid -> {
                    Observable.zip(movieResultsObservable, postResultsObservable, zipFunction)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(movieTitleObserver);
                });
    }

    @NonNull
    private List<String> getMoviesAndPosts(MovieResults movieResults, List<Post> postResults) {
        List<String> moviesAndPosts = new ArrayList<>();
        for (Movie movie : movieResults.getResults()) {
            moviesAndPosts.add(movie.getTitle());
        }
        moviesAndPosts.add("*** -------------------- *** -------------------- ***");
        for (Post post : postResults) {
            moviesAndPosts.add(post.getTitle());
        }
        return moviesAndPosts;
    }
*/
}
