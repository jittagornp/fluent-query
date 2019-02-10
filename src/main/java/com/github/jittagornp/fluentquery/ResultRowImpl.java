/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

import static com.github.jittagornp.fluentquery.StringUtils.hasText;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jitta
 */
public class ResultRowImpl implements ResultRow {

    private Map<String, Object> map;

    private List<String> columnNames;

    public ResultRowImpl(LinkedHashMap<String, Object> map) {
        this.map = map;
        if (map != null) {
            map.forEach((key, value) -> getColumnNames().add(key));
        }
    }

    @Override
    public Boolean getBoolean(String columnName) {
        return get(columnName, Boolean.class);
    }

    @Override
    public Byte getByte(String columnName) {
        return get(columnName, Byte.class);
    }

    @Override
    public Short getShort(String columnName) {
        return get(columnName, Short.class);
    }

    @Override
    public Integer getInteger(String columnName) {
        return get(columnName, Integer.class);
    }

    @Override
    public Long getLong(String columnName) {
        return get(columnName, Long.class);
    }

    @Override
    public Float getFloat(String columnName) {
        return get(columnName, Float.class);
    }

    @Override
    public Double getDouble(String columnName) {
        return get(columnName, Double.class);
    }

    @Override
    public Date getDate(String columnName) {
        return get(columnName, Date.class);
    }

    @Override
    public LocalDate getLocalDate(String columnName) {
        return get(columnName, LocalDate.class);
    }

    @Override
    public LocalDateTime getLocalDateTime(String columnName) {
        return get(columnName, LocalDateTime.class);
    }

    @Override
    public BigDecimal getBigDecimal(String columnName) {
        return get(columnName, BigDecimal.class);
    }

    @Override
    public String getString(String columnName) {
        return get(columnName, String.class);
    }

    @Override
    public Object getObject(String columnName) {
        return getValue(columnName);
    }

    @Override
    public List<String> getColumnNames() {
        if (columnNames == null) {
            columnNames = new ArrayList<>();
        }
        return columnNames;
    }

    @Override
    public Boolean getBoolean(int columnIndex) {
        return get(columnIndex, Boolean.class);
    }

    @Override
    public Byte getByte(int columnIndex) {
        return get(columnIndex, Byte.class);
    }

    @Override
    public Short getShort(int columnIndex) {
        return get(columnIndex, Short.class);
    }

    @Override
    public Integer getInteger(int columnIndex) {
        return get(columnIndex, Integer.class);
    }

    @Override
    public Long getLong(int columnIndex) {
        return get(columnIndex, Long.class);
    }

    @Override
    public Float getFloat(int columnIndex) {
        return get(columnIndex, Float.class);
    }

    @Override
    public Double getDouble(int columnIndex) {
        return get(columnIndex, Double.class);
    }

    @Override
    public Date getDate(int columnIndex) {
        return get(columnIndex, Date.class);
    }

    @Override
    public LocalDate getLocalDate(int columnIndex) {
        return get(columnIndex, LocalDate.class);
    }

    @Override
    public LocalDateTime getLocalDateTime(int columnIndex) {
        return get(columnIndex, LocalDateTime.class);
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex) {
        return get(columnIndex, BigDecimal.class);
    }

    @Override
    public String getString(int columnIndex) {
        return get(columnIndex, String.class);
    }

    @Override
    public Object getObject(int columnIndex) {
        return getValue(columnIndex);
    }

    @Override
    public Map<String, Object> toMap() {
        if (map == null) {
            map = new LinkedHashMap<>();
        }
        return map;
    }

    private Object getValue(String columnName) {
        if (!hasText(columnName)) {
            throw new RuntimeException("Column " + columnName + " not found.");
        }
        Map<String, Object> m = toMap();
        String key = columnName.toUpperCase();
        if (!m.containsKey(key)) {
            throw new RuntimeException("Column " + key + " not found.");
        }
        return m.get(key);
    }

    private Object getValue(int columnIndex) {
        return getValue(getColumnNames().get(columnIndex));
    }

    @Override
    public <T> T get(String columnName, Class<T> typeClass) {
        Object value = getValue(columnName);
        if (value == null) {
            return null;
        }
        TypeConverter<T> converter = TypeConverters.get(typeClass);
        if (converter == null) {
            throw new FluentQueryException("TypeConverter " + typeClass.getName() + " not found.");
        }
        return converter.convert(value);
    }

    @Override
    public <T> T get(int columnIndex, Class<T> typeClass) {
        return get(getColumnNames().get(columnIndex), typeClass);
    }

}
