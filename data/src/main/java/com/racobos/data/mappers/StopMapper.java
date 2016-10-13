package com.racobos.data.mappers;

import com.racobos.data.entities.StopEntity;
import com.racobos.domain.models.StopStation;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by raulcobos on 11/10/16.
 */
@Singleton
public class StopMapper extends Mapper<StopEntity, StopStation> {

    @Inject
    public StopMapper() {
    }

    @Override
    public StopEntity mapModelToEntity(StopStation model) {
        if (model == null) {
            return null;
        }
        StopEntity stopEntity = new StopEntity();
        List<Double> loc = new ArrayList<>();
        loc.add(model.getLat());
        loc.add(model.getLon());
        stopEntity.setLoc(loc);
        stopEntity.setName(model.getAddress());
        stopEntity.setAddr(model.getAddress());
        stopEntity.setCity(model.getCity());
        stopEntity.setCountry(model.getCountry());
        return stopEntity;
    }

    @Override
    public StopStation mapEntityToModel(StopEntity entity) {
        if (entity == null) {
            return null;
        }
        StopStation stopStation = new StopStation();
        if (entity.getLoc() != null && entity.getLoc().size() == 2) {
            stopStation.setLat(entity.getLoc().get(0));
            stopStation.setLon(entity.getLoc().get(1));
        }
        stopStation.setAddress(entity.getAddr());
        stopStation.setCity(entity.getCity());
        stopStation.setCountry(entity.getCountry());
        return stopStation;
    }
}