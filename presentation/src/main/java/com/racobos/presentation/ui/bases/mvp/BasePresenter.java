package com.racobos.presentation.ui.bases.mvp;

import android.support.annotation.CallSuper;
import android.support.annotation.VisibleForTesting;
import com.racobos.domain.errors.ErrorManager;
import com.racobos.presentation.idlingresource.SimpleIdlingResource;

/**
 * Created by rulo7 on 07/10/2016.
 */

public abstract class BasePresenter<T extends BaseView> {

    private T view;

    private ErrorManager errorManager;

    private SimpleIdlingResource simpleIdlingResource;

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

    //<editor-fold desc="Testing">
    protected void isIdleFreeForResource(boolean isWaiting) {
        if (simpleIdlingResource != null) {
            simpleIdlingResource.setIdleState(isWaiting);
        }
    }

    @VisibleForTesting
    public void setSimpleIdlingResource(SimpleIdlingResource simpleIdlingResource) {
        this.simpleIdlingResource = simpleIdlingResource;
    }
    //</editor-fold>
}
