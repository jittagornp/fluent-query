/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author jitta
 */
public interface ResultRow {

    Boolean getBoolean(String columnName);

    Short getShort(String columnName);

    Integer getInteger(String columnName);

    Long getLong(String columnName);

    Float getFloat(String columnName);

    Double getDouble(String columnName);

    Date getDate(String columnName);

    LocalDate getLocalDate(String columnName);

    LocalDateTime getLocalDateTime(String columnName);

    BigDecimal getBigDecimal(String columnName);

    String getString(String columnName);
    
    Object getObject(String columnName);

}
