package com.racobos.data.mappers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by rulo7 on 12/10/2016.
 */
@Singleton
public class DateMapper {

    @Inject
    public DateMapper() {
    }

    public String getCabifyDateFormat(Long timeInMillis) {
        if (timeInMillis == null) {
            return null;
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return simpleDateFormat.format(new Date(timeInMillis));
        }
    }
}