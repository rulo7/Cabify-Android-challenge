package com.racobos.presentation.ui.bases.mvp;

import android.support.annotation.CallSuper;

/**
 * Created by rulo7 on 07/10/2016.
 */

public abstract class BasePresenter<T extends BaseView> {

    private T view;

    protected T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
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
