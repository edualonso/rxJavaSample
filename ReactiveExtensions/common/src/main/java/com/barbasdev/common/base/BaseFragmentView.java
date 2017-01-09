package com.barbasdev.common.base;

import android.support.annotation.IdRes;
import android.view.View;

/**
 * Created by edu on 09/01/2017.
 */
public interface BaseFragmentView<T extends View> {
    T getView(@IdRes int resId);
}
