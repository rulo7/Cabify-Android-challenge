package com.racobos.presentation.ui.bases.android;

import android.app.Application;

import com.racobos.presentation.di.components.ApplicationComponent;
import com.racobos.presentation.di.components.DaggerApplicationComponent;
import com.racobos.presentation.di.modules.ApplicationModule;

/**
 * Created by rulo7 on 07/10/2016.
 */

public abstract class BaseApplication extends Application {

    private ApplicationComponent applicationComponent;

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        initInjector();
        super.onCreate();
    }

    private void initInjector() {
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        applicationComponent.inject(this);
    }
}