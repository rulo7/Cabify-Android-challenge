package com.racobos.presentation.ui.bases.android;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.racobos.presentation.di.ComponentReflectionInjector;
import com.racobos.presentation.di.components.DaggerFragmentComponent;
import com.racobos.presentation.di.components.FragmentComponent;
import com.racobos.presentation.ui.bases.mvp.BasePresenter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import timber.log.Timber;

/**
 * Created by rulo7 on 07/10/2016.
 */

public abstract class BaseFragment extends Fragment {

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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initInjector();
        super.onViewCreated(view, savedInstanceState);
        findPresenter();
        if (presenter != null) {
            presenter.onStart();
        }
    }

    private void initInjector() {
        FragmentComponent component = DaggerFragmentComponent.builder()
                .applicationComponent(((BaseApplication) getActivity().getApplication()).getApplicationComponent())
                .build();
        ComponentReflectionInjector<FragmentComponent> injector =
                new ComponentReflectionInjector<>(FragmentComponent.class, component);
        injector.inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.onUpdate();
        }
    }

    @Override
    public void onPause() {
        if (presenter != null) {
            presenter.onDestroy();
        }
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        if (presenter != null) {
            presenter.onDestroy();
        }
        super.onDestroyView();
    }
}
