package com.racobos.presentation.ui.ratecalculator;

import com.racobos.domain.errors.ErrorCallback;
import com.racobos.domain.models.Journey;
import com.racobos.domain.models.StopStation;
import com.racobos.domain.usecases.EstimateJourney;
import com.racobos.presentation.di.scopes.PerActivity;
import com.racobos.presentation.ui.bases.android.UseCase;
import com.racobos.presentation.ui.bases.mvp.BasePresenter;
import com.racobos.presentation.ui.bases.mvp.BaseView;

import java.util.List;

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

    @UseCase
    EstimateJourney estimateJourney;

    @Inject
    public RateCalculatorPresenter(EstimateJourney estimateJourney) {
        this.estimateJourney = estimateJourney;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (destination == null) {
            getView().showSubmitDestinationRequiredMessage();
        } else {
            getView().setDestination(destination.getLat(), destination.getLon());
        }
        if (origin == null) {
            getView().showSubmitOriginRequiredMessage();
        } else {
            getView().setOrigin(origin.getLat(), origin.getLon());
        }
        if (destination != null && origin != null) {
            getView().showSubmitMessage();
        }
    }

    public void setOrigin(double lat, double lng) {
        origin = new StopStation();
        origin.setLat(lat);
        origin.setLon(lng);
        if (destination == null) {
            getView().showSubmitDestinationRequiredMessage();
        } else {
            getView().showSubmitMessage();
        }
    }

    public void setDestination(double lat, double lng) {
        destination = new StopStation();
        destination.setLat(lat);
        destination.setLon(lng);
        if (origin == null) {
            getView().showSubmitOriginRequiredMessage();
        } else {
            getView().showSubmitMessage();
        }
    }

    public void submit() {
        if (origin != null && destination != null) {
            getView().requestStartMoment(datetime -> estimate(datetime));
        }
    }

    private void estimate(Long timeInMillis) {
        if (timeInMillis != null && timeInMillis < System.currentTimeMillis()) {
            getView().showErrorDateSelected();
        } else {
            getView().showProgress();
            estimateJourney.setParams(timeInMillis, origin, destination);
            isIdleFreeForResource(false);
            estimateJourney.execute(this::onGetRates, this::onError);
        }
    }

    private void onError(Throwable throwable) {
        isIdleFreeForResource(true);
        getView().hideProgress();
        getErrorManager().handleThrowableError(new ErrorCallback() {
            @Override
            public void onHttpError(int status, String message) {
                if (status == 404) {
                    getView().notifyErrorSelectedPlaces();
                } else if (status == 400) {
                    getView().showErrorDateSelected();
                }
            }

            @Override
            public void onNetworkError(String message) {
                getView().notifyErrorNetwork();
            }

            @Override
            public void unknownError(String message) {
                getView().notifySomethingWentWrong();
            }
        }, throwable);
    }

    private void onGetRates(List<Journey> journeys) {
        isIdleFreeForResource(true);
        getView().hideProgress();
        getView().navigateToJourneyRatesList(journeys);
    }

    public interface RateCalculatorView extends BaseView {
        void showProgress();

        void hideProgress();

        void showSubmitMessage();

        void showSubmitOriginRequiredMessage();

        void showSubmitDestinationRequiredMessage();

        void requestStartMoment(OnRequestDateTimeListener onRequestDateTimeListener);

        void showErrorDateSelected();

        void setDestination(Double lat, Double lon);

        void setOrigin(Double lat, Double lon);

        void notifySomethingWentWrong();

        void navigateToJourneyRatesList(List<Journey> journeys);

        void notifyErrorNetwork();

        void notifyErrorSelectedPlaces();

        interface OnRequestDateTimeListener {
            void onDateTimeResponse(Long datetime);
        }
    }
}
