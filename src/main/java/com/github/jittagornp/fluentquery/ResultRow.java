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

    Boolean getBoolean(int columnIndex);

    Byte getByte(String columnName);

    Byte getByte(int columnIndex);

    Short getShort(String columnName);

    Short getShort(int columnIndex);

    Integer getInteger(String columnName);

    Integer getInteger(int columnIndex);

    Long getLong(String columnName);

    Long getLong(int columnIndex);

    Float getFloat(String columnName);

    Float getFloat(int columnIndex);

    Double getDouble(String columnName);

    Double getDouble(int columnIndex);

    Date getDate(String columnName);

    Date getDate(int columnIndex);

    LocalDate getLocalDate(String columnName);

    LocalDate getLocalDate(int columnIndex);

    LocalDateTime getLocalDateTime(String columnName);

    LocalDateTime getLocalDateTime(int columnIndex);

    BigDecimal getBigDecimal(String columnName);

    BigDecimal getBigDecimal(int columnIndex);

    String getString(String columnName);

    String getString(int columnIndex);

    Object getObject(String columnName);

    Object getObject(int columnIndex);

    <T> T get(String columnName, Class<T> typeClass);

    <T> T get(int columnIndex, Class<T> typeClass);

    Map<String, Object> toMap();

}
