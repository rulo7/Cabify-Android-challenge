package com.racobos.domain.usecases;

import com.racobos.domain.executors.PostExecutionThread;
import com.racobos.domain.executors.ThreadExecutor;
import com.racobos.domain.models.StopStation;
import com.racobos.domain.repositories.CabifyApiRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by rulo7 on 09/10/2016.
 */
public class EstimateJourney extends UseCase {

    private CabifyApiRepository cabifyApiRepository;
    private List<StopStation> stops = new ArrayList<>();

    @Inject
    public EstimateJourney(CabifyApiRepository cabifyApiRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.cabifyApiRepository = cabifyApiRepository;
    }

    public void setStops(List<StopStation> stops) {
        this.stops.addAll(stops);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return cabifyApiRepository.estimateJourney(stops);
    }
}
