package com.barbasdev.common.base;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by edu on 17/02/2017.
 */
@Singleton
@Component(modules = {

})
public interface BaseComponent {

    void inject(Object whatever);

}
