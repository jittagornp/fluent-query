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
import java.util.List;
import java.util.Map;

/**
 *
 * @author jitta
 */
public interface ResultRow {

    List<String> getColumnNames();

    Boolean getBoolean(String columnName);

    Boolean getBoolean(int index);

    Byte getByte(String columnName);

    Byte getByte(int index);

    Short getShort(String columnName);

    Short getShort(int index);

    Integer getInteger(String columnName);

    Integer getInteger(int index);

    Long getLong(String columnName);

    Long getLong(int index);

    Float getFloat(String columnName);

    Float getFloat(int index);

    Double getDouble(String columnName);

    Double getDouble(int index);

    Date getDate(String columnName);

    Date getDate(int index);

    LocalDate getLocalDate(String columnName);

    LocalDate getLocalDate(int index);

    LocalDateTime getLocalDateTime(String columnName);

    LocalDateTime getLocalDateTime(int index);

    BigDecimal getBigDecimal(String columnName);

    BigDecimal getBigDecimal(int index);

    String getString(String columnName);

    String getString(int index);

    Object getObject(String columnName);

    Object getObject(int index);

    Map<String, Object> toMap();

}
