package com.racobos.data.mappers;

import com.racobos.data.entities.VehicleTypeEntity;
import com.racobos.domain.models.Vehicle;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by raulcobos on 11/10/16.
 */
@Singleton
public class VehicleMapper extends Mapper<VehicleTypeEntity, Vehicle> {

    @Inject
    public VehicleMapper() {
    }

    @Override
    public VehicleTypeEntity mapModelToEntity(Vehicle model) {
        return null;
    }

    @Override
    public Vehicle mapEntityToModel(VehicleTypeEntity entity) {
        return null;
    }
}
