package com.racobos.presentation.navigation;

import android.app.Activity;

import com.racobos.domain.models.JourneyRate;
import com.racobos.presentation.ui.ratecalculator.RateCalculatorActivity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by rulo7 on 08/10/2016.
 */
@Singleton
public class Navigator {
    @Inject
    public Navigator() {
        // Default empty constructor for dagger injection
    }

    public void navigateToRateCalculator(Activity activity) {
        activity.startActivity(RateCalculatorActivity.getCallingIntent(activity));
    }

    public void navigateToRateList(List<JourneyRate> journeyRates, Activity activity) {

    }
}
