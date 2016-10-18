package com.racobos.presentation.ui.bases.mvp;

import android.support.annotation.CallSuper;

import com.racobos.domain.errors.ErrorManager;
import com.racobos.domain.usecases.base.BaseUseCase;
import com.racobos.presentation.idlingresource.SimpleIdlingResource;
import com.racobos.presentation.ui.bases.android.UseCase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by rulo7 on 07/10/2016.
 */

public abstract class BasePresenter<T extends BaseView> {

    private List<BaseUseCase> baseUseCases = new ArrayList<>();
    private T view;
    private ErrorManager errorManager;
    private SimpleIdlingResource simpleIdlingResource;

    private void findUseCases() {
        for (Field field : getClass().getDeclaredFields()) {
            if (field.getAnnotation(UseCase.class) != null) {
                try {
                    field.setAccessible(true);
                    BaseUseCase baseUseCase = (BaseUseCase) field.get(this);
                    if (baseUseCase != null) {
                        baseUseCases.add(baseUseCase);
                    }
                } catch (IllegalAccessException e) {
                    Timber.e(e,
                            "The field with the annotation @Presenter has to extends from BasePresenter and cannot be private access");
                }
            }
        }
    }

    protected T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public ErrorManager getErrorManager() {
        return errorManager;
    }

    public void setErrorManager(ErrorManager errorManager) {
        this.errorManager = errorManager;
    }

    @CallSuper
    public void onStart() {
    }

    @CallSuper
    public void onUpdate() {
    }

    @CallSuper
    public void onPause() {
    }

    @CallSuper
    public void onDestroy() {
        for (BaseUseCase baseUseCase : baseUseCases) {
            baseUseCase.unsubscribe();
        }
        isIdleFreeForResource(true);
    }

    //<editor-fold desc="Testing">
    protected void isIdleFreeForResource(boolean isWaiting) {
        if (simpleIdlingResource != null) {
            simpleIdlingResource.setIdleState(isWaiting);
        }
    }

    public void setSimpleIdlingResource(SimpleIdlingResource simpleIdlingResource) {
        this.simpleIdlingResource = simpleIdlingResource;
    }
    //</editor-fold>
}
