package com.racobos.domain.repositories;

import com.racobos.domain.models.JourneyRate;
import com.racobos.domain.models.StopStation;
import java.util.List;
import rx.Observable;

/**
 * Created by rulo7 on 09/10/2016.
 */

public interface CabifyApiRepository {
    Observable<List<JourneyRate>> estimateJourney(List<StopStation> stops, String startsAt);
}
