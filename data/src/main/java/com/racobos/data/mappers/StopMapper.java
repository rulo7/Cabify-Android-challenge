package com.racobos.data.mappers;

import com.racobos.data.entities.StopEntity;
import com.racobos.domain.models.StopStation;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by raulcobos on 11/10/16.
 */
@Singleton
public class StopMapper extends Mapper<StopEntity, StopStation> {

    private RateMapper rateMapper;

    @Inject
    public StopMapper(RateMapper rateMapper) {
        this.rateMapper = rateMapper;
    }

    @Override
    public StopEntity mapModelToEntity(StopStation model) {
        return null;
    }

    @Override
    public StopStation mapEntityToModel(StopEntity entity) {
        return null;
    }
}
