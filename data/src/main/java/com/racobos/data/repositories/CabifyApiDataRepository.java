package com.racobos.data.repositories;

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

    @Inject
    public CabifyApiDataRepository() {
        //TODO add datasources and mappers
    }

    @Override
    public Observable<List<JourneyRate>> estimateJourney(List<StopStation> stops) {
        return null;
    }
}
