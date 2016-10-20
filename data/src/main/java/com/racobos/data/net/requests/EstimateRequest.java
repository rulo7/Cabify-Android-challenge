package com.racobos.data.net.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.racobos.data.entities.StopEntity;
import java.util.List;

/**
 * Created by raulcobos on 13/10/16.
 */
public class EstimateRequest {
    @SerializedName("stops")
    @Expose
    List<StopEntity> stops;
    @SerializedName("start_at")
    @Expose
    String startsAt;

    public EstimateRequest(List<StopEntity> stops, String startsAt) {
        this.stops = stops;
        this.startsAt = startsAt;
    }
}