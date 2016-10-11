package com.racobos.data.mappers;

import com.racobos.data.entities.RateEntity;
import com.racobos.domain.models.JourneyRate;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by raulcobos on 11/10/16.
 */
@Singleton
public class RateMapper extends Mapper<RateEntity, JourneyRate> {

    @Inject
    public RateMapper() {
    }

    @Override
    public RateEntity mapModelToEntity(JourneyRate model) {
        return null;
    }

    @Override
    public JourneyRate mapEntityToModel(RateEntity entity) {
        return null;
    }
}
