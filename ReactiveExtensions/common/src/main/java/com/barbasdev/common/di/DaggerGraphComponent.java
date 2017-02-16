package com.barbasdev.common.di;

import com.barbasdev.common.base.BaseActivity;
import com.barbasdev.common.base.BaseApplication;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        MainModule.class
})
public interface DaggerGraphComponent {

    void inject(BaseActivity baseActivity);

    final class Initializer {
        private Initializer() {

        }

        public static DaggerGraphComponent init(BaseApplication application) {
            return DaggerDaggerGraphComponent.builder()
                    .mainModule(new MainModule(application))
                    .build();
        }
    }
}