package com.racobos.data.mappers;

import com.racobos.data.entities.RateEntity;
import com.racobos.domain.models.Journey;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by raulcobos on 11/10/16.
 */
@Singleton
public class RateMapper extends Mapper<RateEntity, Journey> {

    private VehicleMapper vehicleMapper;

    @Inject
    public RateMapper(VehicleMapper vehicleMapper) {
        this.vehicleMapper = vehicleMapper;
    }

    @Override
    public RateEntity mapModelToEntity(Journey model) {
        if (model == null) {
            return null;
        }
        RateEntity rateEntity = new RateEntity();
        rateEntity.setCurrency(model.getCurrency());
        rateEntity.setCurrencySymbol(model.getCurrencySymbol());
        rateEntity.setEta(model.getMinSecondsToArrive(), model.getMaxSecondsToArrive(), model.getTimeEstimation());
        rateEntity.setFormattedPrice(model.getFormattedPrice());
        rateEntity.setTotalPrice(model.getTotalPrice());
        rateEntity.setVehicleType(vehicleMapper.mapModelToEntity(model.getVehicle()));
        return rateEntity;
    }

    @Override
    public Journey mapEntityToModel(RateEntity entity) {
        if (entity == null) {
            return null;
        }
        Journey journey = new Journey();
        journey.setCurrencySymbol(entity.getCurrencySymbol());
        journey.setCurrency(entity.getCurrency());
        journey.setFormattedPrice(entity.getFormattedPrice());
        if (entity.getEta() != null) {
            journey.setMaxSecondsToArrive(entity.getEta().getMax());
            journey.setMinSecondsToArrive(entity.getEta().getMin());
            journey.setTimeEstimation(entity.getEta().getFormatted());
        }
        journey.setTotalPrice(entity.getTotalPrice());
        journey.setVehicle(vehicleMapper.mapEntityToModel(entity.getVehicleType()));
        return journey;
    }
}
