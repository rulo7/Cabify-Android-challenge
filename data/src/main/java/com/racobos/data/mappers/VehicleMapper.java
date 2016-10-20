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
        if (model == null) {
            return null;
        }
        VehicleTypeEntity vehicleTypeEntity = new VehicleTypeEntity();
        vehicleTypeEntity.setName(model.getName());
        vehicleTypeEntity.setAsapOnly(model.getAsapOnly());
        vehicleTypeEntity.setDescription(model.getDescription());
        vehicleTypeEntity.setIcon(model.getImage());
        vehicleTypeEntity.setId(model.getId());
        vehicleTypeEntity.setReservedOnly(model.getReservedOnly());
        vehicleTypeEntity.setShortName(model.getShortName());
        vehicleTypeEntity.setEta(model.getMinSecondsToArrive(), model.getMaxSecondsToArrive(), model.getTimeEstimation());
        return vehicleTypeEntity;
    }

    @Override
    public Vehicle mapEntityToModel(VehicleTypeEntity entity) {
        if (entity == null) {
            return null;
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setShortName(entity.getShortName());
        vehicle.setReservedOnly(entity.getReservedOnly());
        vehicle.setId(entity.getId());
        vehicle.setAsapOnly(entity.getAsapOnly());
        vehicle.setDescription(entity.getDescription());
        if (entity.getIcons() != null) {
            vehicle.setImage(entity.getIcons().getRegular());
        }
        vehicle.setName(entity.getName());
        if (entity.getEta() != null) {
            vehicle.setMaxSecondsToArrive(entity.getEta().getMax());
            vehicle.setMinSecondsToArrive(entity.getEta().getMin());
            vehicle.setTimeEstimation(entity.getEta().getFormatted());
        }
        return vehicle;
    }
}
