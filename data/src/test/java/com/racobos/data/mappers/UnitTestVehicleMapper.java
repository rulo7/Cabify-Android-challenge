package com.racobos.data.mappers;

import com.racobos.data.entities.VehicleTypeEntity;
import com.racobos.domain.models.Vehicle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by rulo7 on 14/10/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class UnitTestVehicleMapper {

    @InjectMocks
    VehicleMapper vehicleMapper;

    @Test
    public void testMapVehicleEntityToModel() {
        VehicleTypeEntity expected = VehicleTypeEntity.getMockObject();
        Vehicle result = vehicleMapper.mapEntityToModel(expected);
        assertEquals(result.getAsapOnly(), expected.getAsapOnly());
        assertEquals(result.getDescription(), expected.getDescription());
        assertEquals(result.getId(), expected.getId());
        assertEquals(result.getImage(), expected.getIcons().getRegular());
        assertEquals(result.getMaxSecondsToArrive(), expected.getEta().getMax());
        assertEquals(result.getMinSecondsToArrive(), expected.getEta().getMin());
        assertEquals(result.getTimeEstimation(), expected.getEta().getFormatted());
        assertEquals(result.getName(), expected.getName());
        assertEquals(result.getReservedOnly(), expected.getReservedOnly());
        assertEquals(result.getShortName(), expected.getShortName());
    }
}