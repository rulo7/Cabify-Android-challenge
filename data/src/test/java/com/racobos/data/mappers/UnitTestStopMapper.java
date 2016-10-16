package com.racobos.data.mappers;

import com.racobos.data.entities.StopEntity;
import com.racobos.domain.models.StopStation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by rulo7 on 14/10/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class UnitTestStopMapper {

    @InjectMocks
    StopMapper stopMapper;

    @Test
    public void testMapStopModelToEntity() {
        StopStation expected = new StopStation();
        expected.setLat(0.00000d);
        expected.setLon(0.00000d);
        expected.setAddress("Calle false 123");
        expected.setCity("Madrid");
        expected.setCountry("Spain");
        StopEntity result = stopMapper.mapModelToEntity(expected);
        assertEquals(result.getAddr(), expected.getAddress());
        assertEquals(result.getCity(), expected.getCity());
        assertEquals(result.getCountry(), expected.getCountry());
        assertEquals(result.getLoc().get(0), expected.getLat());
        assertEquals(result.getLoc().get(1), expected.getLon());
        assertEquals(result.getName(), expected.getAddress());
        assertEquals(result.getNum(), null);
    }
}
