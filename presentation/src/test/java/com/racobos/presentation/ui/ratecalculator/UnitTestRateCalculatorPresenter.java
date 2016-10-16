package com.racobos.presentation.ui.ratecalculator;

import com.racobos.domain.usecases.EstimateJourney;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by rulo7 on 16/10/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class UnitTestRateCalculatorPresenter {
    private RateCalculatorPresenter rateCalculatorPresenter;

    @Mock
    private EstimateJourney estimateJourney;
    @Mock
    private RateCalculatorPresenter.RateCalculatorView rateCalculatorView;

    @Before
    public void setUp() {
        rateCalculatorPresenter = new RateCalculatorPresenter(estimateJourney);
        rateCalculatorPresenter.setView(rateCalculatorView);
    }

    @Test
    public void onUpdateMethodOnStartTest() {
        rateCalculatorPresenter.onUpdate();
        verify(rateCalculatorView).showSubmitDestinationRequiredMessage();
        verify(rateCalculatorView).showSubmitOriginRequiredMessage();

        verify(rateCalculatorView, never()).setDestination(anyDouble(), anyDouble());
        verify(rateCalculatorView, never()).setOrigin(anyDouble(), anyDouble());
        verify(rateCalculatorView, never()).showSubmitMessage();
    }

    @Test
    public void onUpdateMethodWhenOriginWasSetTest() {
        rateCalculatorPresenter.setOrigin(0.0d, 0.0d);
        rateCalculatorPresenter.onUpdate();

        verify(rateCalculatorView, atLeast(1)).showSubmitDestinationRequiredMessage();
        verify(rateCalculatorView, never()).showSubmitMessage();
        verify(rateCalculatorView, never()).setDestination(anyDouble(), anyDouble());
        verify(rateCalculatorView, never()).showSubmitOriginRequiredMessage();
        verify(rateCalculatorView).setOrigin(anyDouble(), anyDouble());
    }

    @Test
    public void onUpdateMethodWhenDestinationWasSetTest() {
        rateCalculatorPresenter.setDestination(0.0d, 0.0d);
        rateCalculatorPresenter.onUpdate();

        verify(rateCalculatorView, atLeast(1)).showSubmitOriginRequiredMessage();
        verify(rateCalculatorView, never()).showSubmitMessage();
        verify(rateCalculatorView, never()).setOrigin(anyDouble(), anyDouble());
        verify(rateCalculatorView, never()).showSubmitDestinationRequiredMessage();
        verify(rateCalculatorView).setDestination(anyDouble(), anyDouble());
    }

    @Test
    public void onUpdateMethodWhenOriginAndDestinationWasSetTest() {
        rateCalculatorPresenter.setOrigin(0.0d, 0.0d);
        rateCalculatorPresenter.setDestination(0.0d, 0.0d);
        rateCalculatorPresenter.onUpdate();

        verify(rateCalculatorView, atMost(1)).showSubmitDestinationRequiredMessage();
        verify(rateCalculatorView, atLeast(1)).setDestination(anyDouble(), anyDouble());
        verify(rateCalculatorView, never()).showSubmitOriginRequiredMessage();
        verify(rateCalculatorView, atLeast(1)).setOrigin(anyDouble(), anyDouble());

        verify(rateCalculatorView, atLeast(1)).showSubmitMessage();
    }

    @Test
    public void setOriginTestWithNullDestinationTest() {
        rateCalculatorPresenter.setOrigin(0.0d, 0.0d);
        verify(rateCalculatorView).showSubmitDestinationRequiredMessage();
        verify(rateCalculatorView, never()).showSubmitMessage();
    }

    @Test
    public void setOriginTestWithNotNullDestinationTest() {
        rateCalculatorPresenter.setDestination(0.0d, 0.0d);
        rateCalculatorPresenter.setOrigin(0.0d, 0.0d);
        verify(rateCalculatorView, never()).showSubmitDestinationRequiredMessage();
        verify(rateCalculatorView, atLeast(1)).showSubmitMessage();
    }

    @Test
    public void setDestinationTestWithNullOriginTest() {
        rateCalculatorPresenter.setDestination(0.0d, 0.0d);
        verify(rateCalculatorView).showSubmitOriginRequiredMessage();
        verify(rateCalculatorView, never()).showSubmitMessage();
    }

    @Test
    public void setDestinationTestWithNotNullOriginTest() {
        rateCalculatorPresenter.setOrigin(0.0d, 0.0d);
        rateCalculatorPresenter.setDestination(0.0d, 0.0d);
        verify(rateCalculatorView, never()).showSubmitOriginRequiredMessage();
        verify(rateCalculatorView, atLeast(1)).showSubmitMessage();
    }

    @Test
    public void verifySubmitHappyWithoutOriginAndDestinationSetTest() {
        rateCalculatorPresenter.submit();
        verify(rateCalculatorView, never()).requestStartMoment(any());
    }

    @Test
    public void verifySubmitHappyWithOriginAndDestinationSetTest() {
        rateCalculatorPresenter.setOrigin(0.0d, 0.0d);
        rateCalculatorPresenter.setDestination(0.0d, 0.0d);
        rateCalculatorPresenter.submit();
        verify(rateCalculatorView).requestStartMoment(any());
    }
}
