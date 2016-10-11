package com.racobos.domain.usecases;

import com.racobos.domain.executors.PostExecutionThread;
import com.racobos.domain.executors.ThreadExecutor;
import com.racobos.domain.models.StopStation;
import com.racobos.domain.repositories.CabifyApiRepository;
import com.racobos.domain.usecases.base.UseCase;
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
    private String startsAt;

    @Inject
    public EstimateJourney(CabifyApiRepository cabifyApiRepository, ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.cabifyApiRepository = cabifyApiRepository;
    }

    public void setParams(List<StopStation> stops, String startsAt) {
        this.stops.addAll(stops);
        this.startsAt = startsAt;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return cabifyApiRepository.estimateJourney(stops, startsAt);
    }
}
