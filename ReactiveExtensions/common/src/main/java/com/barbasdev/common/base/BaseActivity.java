package com.barbasdev.common.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.barbasdev.common.R;

public abstract class BaseActivity extends AppCompatActivity {

    public final static String ARG_VIEWMODEL = "ARG_VIEWMODEL";

    /**
     *
     * @param context
     * @param fragmentClass
     * @param <F>
     * @return
     */
    public static <F extends BaseFragment> F instantiateFragment(Context context, Class<F> fragmentClass) {
        return instantiateFragment(context, fragmentClass, null, null);
    }

    /**
     *
     * @param context
     * @param fragmentClass
     * @param viewModel
     * @param <F>
     * @param <V>
     * @return
     */
    public static <F extends BaseFragment, V extends BaseViewModel> F instantiateFragment(Context context, Class<F> fragmentClass, V viewModel) {
        return instantiateFragment(context, fragmentClass, viewModel, null);
    }

    /**
     *
     * @param context
     * @param fragmentClass
     * @param viewModel
     * @param args
     * @param <F>
     * @param <V>
     * @return
     */
    public static <F extends Fragment, V extends BaseViewModel> F instantiateFragment(Context context, Class<F> fragmentClass, @Nullable V viewModel, @Nullable Bundle args) {
        F fragment = (F) Fragment.instantiate(context, fragmentClass.getName());

        if (args == null) {
            args = new Bundle();
        }

        if (viewModel != null) {
            args.putParcelable(ARG_VIEWMODEL, viewModel);
        }

        fragment.setArguments(args);

        return fragment;
    }

    public abstract void injectDependencies();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        injectDependencies();
    }

    /**
     *
     * @param fragment
     * @param <F>
     */
    public <F extends BaseFragment> void showFragment(F fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment, fragment.getClass().getSimpleName())
                .commit();
    }

    public <V extends BaseViewModel> V getFragmentViewModel() {
        Intent intent = getIntent();
        if (intent != null) {
            return intent.getParcelableExtra(ARG_VIEWMODEL);
        }
        return null;
    }
}












//package com.barbasdev.common.base;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v7.app.AppCompatActivity;
//
//import com.barbasdev.common.R;
//import com.barbasdev.common.di.HelloService;
//
//import javax.inject.Inject;
//
//public class BaseActivity extends AppCompatActivity {
//
//    public final static String ARG_VIEWMODEL = "ARG_VIEWMODEL";
//
//    /**
//     *
//     * @param context
//     * @param fragmentClass
//     * @param <F>
//     * @return
//     */
//    public static <F extends BaseFragment> F instantiateFragment(Context context, Class<F> fragmentClass) {
//        return instantiateFragment(context, fragmentClass, null, null);
//    }
//
//    /**
//     *
//     * @param context
//     * @param fragmentClass
//     * @param viewModel
//     * @param <F>
//     * @param <V>
//     * @return
//     */
//    public static <F extends BaseFragment, V extends BaseViewModel> F instantiateFragment(Context context, Class<F> fragmentClass, V viewModel) {
//        return instantiateFragment(context, fragmentClass, viewModel, null);
//    }
//
//    /**
//     *
//     * @param context
//     * @param fragmentClass
//     * @param viewModel
//     * @param args
//     * @param <F>
//     * @param <V>
//     * @return
//     */
//    public static <F extends Fragment, V extends BaseViewModel> F instantiateFragment(Context context, Class<F> fragmentClass, @Nullable V viewModel, @Nullable Bundle args) {
//        F fragment = (F) Fragment.instantiate(context, fragmentClass.getName());
//
//        if (args == null) {
//            args = new Bundle();
//        }
//
//        if (viewModel != null) {
//            args.putParcelable(ARG_VIEWMODEL, viewModel);
//        }
//
//        fragment.setArguments(args);
//
//        return fragment;
//    }
//
//    @Inject
//    public HelloService helloService;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base);
//
//        BaseApplication.getGraph().inject(this);
//    }
//
//    /**
//     *
//     * @param fragment
//     * @param <F>
//     */
//    public <F extends BaseFragment> void showFragment(F fragment) {
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragmentContainer, fragment, fragment.getClass().getSimpleName())
//                .commit();
//    }
//
//    public <V extends BaseViewModel> V getFragmentViewModel() {
//        Intent intent = getIntent();
//        if (intent != null) {
//            return intent.getParcelableExtra(ARG_VIEWMODEL);
//        }
//        return null;
//    }
//}
