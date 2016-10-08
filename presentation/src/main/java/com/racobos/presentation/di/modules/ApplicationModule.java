package com.racobos.presentation.di.modules;

import android.content.Context;

import com.racobos.presentation.ui.bases.android.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by raulcobos on 2/9/16.
 */
@Module
public class ApplicationModule {
    private final BaseApplication baseApplication;

    public ApplicationModule(BaseApplication baseApplication) {
        this.baseApplication = baseApplication;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return baseApplication.getApplicationContext();
    }
}