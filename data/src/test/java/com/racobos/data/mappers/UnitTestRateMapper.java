package com.racobos.data.mappers;

import com.racobos.data.entities.RateEntity;
import com.racobos.domain.models.Journey;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by rulo7 on 14/10/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class UnitTestRateMapper {

    @Mock
    VehicleMapper vehicleMapper;
    @InjectMocks
    RateMapper rateMapper;

    @Test
    public void testMapRateEntityToModel() {
        RateEntity expected = RateEntity.getMockObject();
        Journey journey = rateMapper.mapEntityToModel(expected);
        assertEquals(journey.getCurrency(), expected.getCurrency());
        assertEquals(journey.getCurrencySymbol(), expected.getCurrencySymbol());
        assertEquals(journey.getFormattedPrice(), expected.getFormattedPrice());
        Double expectedTotalPrice = expected.getTotalPrice() / 100.0d;
        assertEquals(journey.getTotalPrice(), expectedTotalPrice);
    }
}
