package com.racobos.data.repositories;

import com.racobos.data.entities.RateEntity;
import com.racobos.data.mappers.DateMapper;
import com.racobos.data.mappers.RateMapper;
import com.racobos.data.mappers.StopMapper;
import com.racobos.data.net.CabifyApi;
import com.racobos.data.net.requests.EstimateRequest;
import com.racobos.domain.models.StopStation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by rulo7 on 15/10/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class UnitTestCabifyApiRepository {

    @Mock
    private CabifyApi cabifyApi;
    @Mock
    private StopMapper stopMapper;
    @Mock
    private RateMapper rateMapper;
    @Mock
    private DateMapper dateMapper;
    @InjectMocks
    private CabifyApiDataRepository cabifyApiDataRepository;
    @InjectMocks
    private EstimateRequest estimateRequest;

    private long date = 1476472340191L;


    @Test
    public void testEstimateJourneyFromApi() {
        List<StopStation> stopStations = mock(List.class);
        Observable<List<RateEntity>> fakeObservable = Observable.just(mock(List.class));
        given(cabifyApi.estimateRate(any())).willReturn(fakeObservable);
        cabifyApiDataRepository.estimateJourney(stopStations, date);

        verify(stopMapper).mapModelToEntity(stopStations);
        verify(dateMapper).getCabifyDateFormat(date);
        verify(cabifyApi).estimateRate(any());
    }
}
