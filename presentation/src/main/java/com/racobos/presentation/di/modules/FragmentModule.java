package com.racobos.presentation.di.modules;

import com.racobos.presentation.di.scopes.PerFragment;
import com.racobos.presentation.ui.bases.android.BaseFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by raulcobos on 2/9/16.
 */
@Module
public class FragmentModule {
    private final BaseFragment baseFragment;

    public FragmentModule(BaseFragment baseFragment) {
        this.baseFragment = baseFragment;
    }

    @Provides
    @PerFragment
    BaseFragment provideBaseFragment() {
        return this.baseFragment;
    }
}