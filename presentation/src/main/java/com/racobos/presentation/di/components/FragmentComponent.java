package com.racobos.presentation.di.components;

import com.racobos.presentation.di.modules.FragmentModule;
import com.racobos.presentation.di.scopes.PerFragment;

import dagger.Component;

/**
 * Created by raulcobos on 2/9/16.
 */
@PerFragment
@Component(
        dependencies = ApplicationComponent.class,
        modules = FragmentModule.class)
public interface FragmentComponent {
}
