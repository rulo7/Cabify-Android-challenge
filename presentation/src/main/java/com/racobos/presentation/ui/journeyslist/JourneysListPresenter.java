package com.racobos.presentation.ui.journeyslist;

import com.racobos.domain.models.Journey;
import com.racobos.presentation.di.scopes.PerActivity;
import com.racobos.presentation.ui.bases.mvp.BasePresenter;
import com.racobos.presentation.ui.bases.mvp.BaseView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import icepick.State;

/**
 * Created by raulcobos on 13/10/16.
 */
@PerActivity
public class JourneysListPresenter extends BasePresenter<JourneysListPresenter.JourneysListView> {

    @State
    ArrayList<Journey> journeys;

    @Inject
    public JourneysListPresenter() {
    }

    public void setJourneys(List<Journey> journeys) {
        this.journeys = new ArrayList<>(journeys);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        getView().renderJourneys(journeys);
    }

    public interface JourneysListView extends BaseView {
        void renderJourneys(List<Journey> journeys);
    }
}