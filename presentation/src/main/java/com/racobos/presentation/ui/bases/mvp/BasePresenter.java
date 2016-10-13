package com.racobos.presentation.ui.bases.mvp;

import android.support.annotation.CallSuper;

import com.racobos.domain.errors.ErrorManager;


/**
 * Created by rulo7 on 07/10/2016.
 */

public abstract class BasePresenter<T extends BaseView> {

    private T view;

    private ErrorManager errorManager;

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

    }
}
