package com.racobos.presentation.ui.ratecalculator;

import com.racobos.domain.models.StopStation;
import com.racobos.presentation.di.scopes.PerActivity;
import com.racobos.presentation.ui.bases.mvp.BasePresenter;
import com.racobos.presentation.ui.bases.mvp.BaseView;

import javax.inject.Inject;

import icepick.State;


/**
 * Created by rulo7 on 09/10/2016.
 */
@PerActivity
public class RateCalculatorPresenter extends BasePresenter<RateCalculatorPresenter.RateCalculatorView> {

    @State
    StopStation origin;
    @State
    StopStation destination;

    @Inject
    public RateCalculatorPresenter() {
        // Default empty constructor for dagger injection
    }

    public void setOrigin(double lat, double lng) {

    }

    public void setDestination(double lat, double lng) {

    }

    public void submit() {
        if (origin == null) {
            getView().showErrorEmptyOriginDialog();
        } else if (destination == null) {
            getView().showErrorEmptyDestinationDialog();
        } else {
            calculateRate();
        }
    }

    private void calculateRate() {
        //TODO
    }

    public interface RateCalculatorView extends BaseView {
        void showErrorEmptyOriginDialog();

        void showErrorEmptyDestinationDialog();

        void showProgress();

        void hideProgress();
    }
}
