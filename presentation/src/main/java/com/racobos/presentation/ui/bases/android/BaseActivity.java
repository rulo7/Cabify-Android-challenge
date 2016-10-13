package com.racobos.presentation.ui.bases.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.racobos.domain.errors.ErrorManager;
import com.racobos.presentation.di.ComponentReflectionInjector;
import com.racobos.presentation.di.components.ActivityComponent;
import com.racobos.presentation.di.components.DaggerActivityComponent;
import com.racobos.presentation.ui.bases.mvp.BasePresenter;
import com.racobos.presentation.ui.bases.mvp.BaseView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import icepick.Icepick;
import timber.log.Timber;

/**
 * Created by rulo7 on 07/10/2016.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @Inject
    ErrorManager errorHandler;

    private List<BasePresenter> presenters = new ArrayList<>();

    private void findPresenter() {
        for (Field field : getClass().getDeclaredFields()) {
            if (field.getAnnotation(Presenter.class) != null) {
                try {
                    field.setAccessible(true);
                    BasePresenter presenter = (BasePresenter) field.get(this);
                    if (presenter != null) {
                        presenters.add(presenter);
                    }
                } catch (IllegalAccessException e) {
                    Timber.e(e,
                            "The field with the annotation @Presenter has to extends from BasePresenter and cannot be private access");
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initInjector();
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        findPresenter();
        for (BasePresenter presenter : presenters) {
            presenter.setView(this);
            presenter.setErrorManager(errorHandler);
            Icepick.restoreInstanceState(presenter, savedInstanceState);
            if (savedInstanceState == null) {
                presenter.onStart();
            }
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
        for (BasePresenter presenter : presenters) {
            Icepick.saveInstanceState(presenter, outState);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (BasePresenter presenter : presenters) {
            presenter.onUpdate();
        }
    }

    @Override
    protected void onPause() {
        for (BasePresenter presenter : presenters) {
            presenter.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        for (BasePresenter presenter : presenters) {
            presenter.onDestroy();
        }
        super.onDestroy();
    }
}
