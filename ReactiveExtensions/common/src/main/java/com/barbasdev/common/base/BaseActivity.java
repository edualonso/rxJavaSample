package com.barbasdev.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.barbasdev.common.R;

public class BaseActivity extends AppCompatActivity {

    protected final static String ARG_VIEWMODEL = "ARG_VIEWMODEL";

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
    public static <F extends Fragment, V extends BaseViewModel> F instantiateFragment(Context context, Class<F> fragmentClass, @NonNull V viewModel, @Nullable Bundle args) {
        F fragment = (F) Fragment.instantiate(context, fragmentClass.getName());

        if (args == null) {
            args = new Bundle();
        }
        args.putParcelable(ARG_VIEWMODEL, viewModel);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
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
}
