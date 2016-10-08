package com.racobos.presentation.ui.bases.mvp;

import android.support.annotation.CallSuper;

/**
 * Created by rulo7 on 07/10/2016.
 */

public abstract class BasePresenter<T extends BaseView> {
    public abstract T getView();

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
