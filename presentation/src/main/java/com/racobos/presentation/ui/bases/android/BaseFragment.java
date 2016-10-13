package com.racobos.presentation.ui.bases.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.racobos.domain.errors.ErrorManager;
import com.racobos.presentation.di.ComponentReflectionInjector;
import com.racobos.presentation.di.components.DaggerFragmentComponent;
import com.racobos.presentation.di.components.FragmentComponent;
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

public abstract class BaseFragment extends Fragment implements BaseView {

    @Inject
    ErrorManager errorManager;

    private List<BasePresenter> presenters = new ArrayList<>();

    private void findPresenter() {
        for (Field field : getClass().getDeclaredFields()) {
            if (field.getAnnotation(Presenter.class) != null)
                try {
                    presenters.add((BasePresenter) field.get(this));
                } catch (IllegalAccessException e) {
                    Timber.e(e, "The field with the annotation @Presenter has to extends from BasePresenter");
                }
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initInjector();
        super.onViewCreated(view, savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        findPresenter();
        for (BasePresenter presenter : presenters) {
            presenter.setView(this);
            presenter.setErrorManager(errorManager);
            Icepick.restoreInstanceState(presenter, savedInstanceState);
            if (savedInstanceState == null) {
                presenter.onStart();
            }
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
        for (BasePresenter presenter : presenters) {
            Icepick.saveInstanceState(presenter, outState);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        for (BasePresenter presenter : presenters) {
            presenter.onUpdate();
        }
    }

    @Override
    public void onPause() {
        for (BasePresenter presenter : presenters) {
            presenter.onDestroy();
        }
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        for (BasePresenter presenter : presenters) {
            presenter.onDestroy();
        }
        super.onDestroyView();
    }
}
