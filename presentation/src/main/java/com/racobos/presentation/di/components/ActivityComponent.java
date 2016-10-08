package com.racobos.presentation.di.components;

import com.racobos.presentation.di.modules.ActivityModule;
import com.racobos.presentation.di.scopes.PerActivity;
import com.racobos.presentation.ui.MainActivity;
import com.racobos.presentation.ui.ratecalculator.RateCalculatorActivity;

import dagger.Component;

/**
 * Created by raulcobos on 2/9/16.
 */
@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);

    void inject(RateCalculatorActivity rateCalculatorActivity);
}
