package com.racobos.data.mappers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by rulo7 on 14/10/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class UnitTestDateMapper {

    @InjectMocks
    DateMapper dateMapper;

    @Test
    public void testMapTimeInMillisToCabifyDateFormat() {
        long friOct142016_2112 = 1476472340191L;
        String expected = "2016-10-14 21:12";
        String result = dateMapper.getCabifyDateFormat(friOct142016_2112);
        assertEquals(result, expected);
    }
}
