/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author jitta
 */
public class LocalDateTypeConverter implements TypeConverter<LocalDate>{

    @Override
    public LocalDate convert(Object value) {
        if(!(value instanceof Date)){
            throw new FluentQueryException("Object value it's not Date type.");
        }
        Date date = (Date)value;
        return DateConverterUtils.convert2LocalDate(date);
    }
    
}
