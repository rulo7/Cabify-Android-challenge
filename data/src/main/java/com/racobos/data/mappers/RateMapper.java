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

    private VehicleMapper vehicleMapper;

    @Inject
    public RateMapper(VehicleMapper vehicleMapper) {
        this.vehicleMapper = vehicleMapper;
    }

    @Override
    public RateEntity mapModelToEntity(JourneyRate model) {
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
    public JourneyRate mapEntityToModel(RateEntity entity) {
        if (entity == null) {
            return null;
        }
        JourneyRate journeyRate = new JourneyRate();
        journeyRate.setCurrencySymbol(entity.getCurrencySymbol());
        journeyRate.setCurrency(entity.getCurrency());
        journeyRate.setFormattedPrice(entity.getFormattedPrice());
        if (entity.getEta() != null) {
            journeyRate.setMaxSecondsToArrive(entity.getEta().getMax());
            journeyRate.setMinSecondsToArrive(entity.getEta().getMin());
            journeyRate.setTimeEstimation(entity.getEta().getFormatted());
        }
        journeyRate.setTotalPrice(entity.getTotalPrice());
        journeyRate.setVehicle(vehicleMapper.mapEntityToModel(entity.getVehicleType()));
        return journeyRate;
    }
}
