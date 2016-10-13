package com.racobos.presentation.ui.journeyslist;

import com.racobos.domain.models.Journey;
import com.racobos.presentation.ui.bases.mvp.BasePresenter;
import com.racobos.presentation.ui.bases.mvp.BaseView;
import icepick.State;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raulcobos on 13/10/16.
 */
public class JourneysListPresenter extends BasePresenter<JourneysListPresenter.JourneysListView> {

    @State
    ArrayList<Journey> journeys;

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