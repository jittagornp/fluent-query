/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author jitta
 */
public class DateConverterUtils {

    private DateConverterUtils() {

    }

    public static Date convert2Date(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return convert2Date(localDate.atStartOfDay());
    }

    public static Date convert2Date(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime convert2LocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static LocalDate convert2LocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return convert2LocalDateTime(date).toLocalDate();
    }

}
