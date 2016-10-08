package com.racobos.presentation.di.modules;

import com.racobos.presentation.di.scopes.PerActivity;
import com.racobos.presentation.ui.bases.android.BaseActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by raulcobos on 2/9/16.
 */
@Module
public class ActivityModule {
    private final BaseActivity baseActivity;

    public ActivityModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Provides
    @PerActivity
    BaseActivity provideBaseActivity() {
        return this.baseActivity;
    }
}
