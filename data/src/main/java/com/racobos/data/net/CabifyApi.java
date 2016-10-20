package com.racobos.data.net;

import com.racobos.data.entities.RateEntity;
import com.racobos.data.net.requests.EstimateRequest;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by rulo7 on 16/10/2016.
 */

public interface CabifyApi {
    @POST("api/v2/estimate")
    Observable<List<RateEntity>> estimateRate(@Body EstimateRequest estimateRequest);
}
