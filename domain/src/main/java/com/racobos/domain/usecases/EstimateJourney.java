package com.racobos.domain.usecases;

import com.racobos.domain.executors.PostExecutionThread;
import com.racobos.domain.executors.ThreadExecutor;
import com.racobos.domain.models.Journey;
import com.racobos.domain.models.StopStation;
import com.racobos.domain.repositories.CabifyApiRepository;
import com.racobos.domain.usecases.base.BaseUseCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by rulo7 on 09/10/2016.
 */
public class EstimateJourney extends BaseUseCase<List<Journey>> {

    private CabifyApiRepository cabifyApiRepository;
    private List<StopStation> stops;
    private Long startsAt;

    @Inject
    public EstimateJourney(CabifyApiRepository cabifyApiRepository, ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.cabifyApiRepository = cabifyApiRepository;
    }

    public void setParams(Long startsAt, StopStation... stops) {
        setParams(startsAt, Arrays.asList(stops));
    }

    public void setParams(Long startsAt, List<StopStation> stops) {
        this.stops = new ArrayList<>(stops);
        this.startsAt = startsAt;
    }

    @Override
    protected Observable<List<Journey>> buildUseCaseObservable() {
        return cabifyApiRepository.estimateJourney(stops, startsAt);
    }
}