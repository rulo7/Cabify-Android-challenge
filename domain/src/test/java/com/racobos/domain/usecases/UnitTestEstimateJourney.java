package com.racobos.domain.usecases;

import com.racobos.domain.executors.PostExecutionThread;
import com.racobos.domain.executors.ThreadExecutor;
import com.racobos.domain.models.StopStation;
import com.racobos.domain.repositories.CabifyApiRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by rulo7 on 15/10/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class UnitTestEstimateJourney {
    private static final long FAKE_DATETIME = 1476472340191L;
    private static final List<StopStation> stops = new ArrayList<>();

    private EstimateJourney estimateJourney;

    @Mock
    private CabifyApiRepository cabifyApiRepository;
    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;


    @Before
    public void setUp() {
        estimateJourney = new EstimateJourney(cabifyApiRepository, mockThreadExecutor,
                mockPostExecutionThread);
        stops.add(mock(StopStation.class));
        stops.add(mock(StopStation.class));
    }

    @After
    public void clear() {
        stops.clear();
    }

    @Test
    public void testGetUserListUseCaseObservableHappyCase() {
        estimateJourney.setParams(FAKE_DATETIME, stops);
        estimateJourney.buildUseCaseObservable();

        verify(cabifyApiRepository).estimateJourney(stops, FAKE_DATETIME);
        verifyNoMoreInteractions(cabifyApiRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
