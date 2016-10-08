package com.racobos.presentation.ui.bases.android;

import android.app.Activity;
import android.os.Bundle;

import com.racobos.presentation.di.ComponentReflectionInjector;
import com.racobos.presentation.di.components.ActivityComponent;
import com.racobos.presentation.di.components.DaggerActivityComponent;
import com.racobos.presentation.ui.bases.mvp.BasePresenter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import timber.log.Timber;

/**
 * Created by rulo7 on 07/10/2016.
 */

public abstract class BaseActivity extends Activity {

    private BasePresenter presenter;

    private void findPresenter() {
        for (Field field : getClass().getDeclaredFields()) {
            for (Annotation annotation : field.getDeclaredAnnotations()) {
                if (annotation.annotationType().equals(Presenter.class))
                    try {
                        field.get(presenter);
                    } catch (IllegalAccessException e) {
                        Timber.e(e, "The field with the annotation @Presenter has to extends from BasePresenter");
                    }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initInjector();
        super.onCreate(savedInstanceState);
        findPresenter();
        if (presenter != null) {
            presenter.onStart();
        }
    }

    private void initInjector() {
        ActivityComponent component = DaggerActivityComponent.builder()
                .applicationComponent(((BaseApplication) getApplication()).getApplicationComponent())
                .build();
        ComponentReflectionInjector<ActivityComponent> injector =
                new ComponentReflectionInjector<>(ActivityComponent.class, component);
        injector.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.onUpdate();
        }
    }

    @Override
    protected void onPause() {
        if (presenter != null) {
            presenter.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.onDestroy();
        }
        super.onDestroy();
    }
}
