package com.racobos.presentation.di.components;

import android.content.Context;

import com.racobos.presentation.di.modules.ApplicationModule;
import com.racobos.presentation.navigation.Navigator;
import com.racobos.presentation.ui.bases.android.BaseApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by raulcobos on 2/9/16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseApplication baseApplication);

    Context getContext();

    Navigator navigator();
}
