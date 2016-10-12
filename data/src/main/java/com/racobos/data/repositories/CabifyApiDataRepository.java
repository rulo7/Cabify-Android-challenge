package com.racobos.data.repositories;

import com.racobos.data.mappers.DateMapper;
import com.racobos.data.mappers.RateMapper;
import com.racobos.data.mappers.StopMapper;
import com.racobos.data.net.CabifyApiServices;
import com.racobos.domain.models.JourneyRate;
import com.racobos.domain.models.StopStation;
import com.racobos.domain.repositories.CabifyApiRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by rulo7 on 10/10/2016.
 */
@Singleton
public class CabifyApiDataRepository implements CabifyApiRepository {

    private StopMapper stopMapper;
    private RateMapper rateMapper;
    private DateMapper dateMapper;

    @Inject
    public CabifyApiDataRepository(StopMapper stopMapper, RateMapper rateMapper, DateMapper dateMapper) {
        this.stopMapper = stopMapper;
        this.rateMapper = rateMapper;
        this.dateMapper = dateMapper;
    }

    @Override
    public Observable<List<JourneyRate>> estimateJourney(List<StopStation> stops, Long startsAt) {
        return CabifyApiServices.getApi()
                .estimateRate(stopMapper.mapModelToEntity(stops), dateMapper.getCabifyDateFortmat(startsAt))
                .map(rateEntities -> rateMapper.mapEntityToModel(rateEntities));
    }
}
