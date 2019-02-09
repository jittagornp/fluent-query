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

    private Object getValue(String columnName) {
        if (!hasText(columnName)) {
            throw new RuntimeException("Column " + columnName + " not found.");
        }
        String key = columnName.toUpperCase();
        if (!toMap().containsKey(key)) {
            throw new RuntimeException("Column " + key + " not found.");
        }
        return toMap().get(key);
    }

    private Object getValue(int index) {
        return getValue(getColumnNames().get(index));
    }

    @Override
    public Boolean getBoolean(String columnName) {
        return (Boolean) getValue(columnName);
    }

    @Override
    public Byte getByte(String columnName) {
        return (Byte) getValue(columnName);
    }

    @Override
    public Short getShort(String columnName) {
        return (Short) getValue(columnName);
    }

    @Override
    public Integer getInteger(String columnName) {
        return (Integer) getValue(columnName);
    }

    @Override
    public Long getLong(String columnName) {
        return (Long) getValue(columnName);
    }

    @Override
    public Float getFloat(String columnName) {
        return (Float) getValue(columnName);
    }

    @Override
    public Double getDouble(String columnName) {
        return (Double) getValue(columnName);
    }

    @Override
    public Date getDate(String columnName) {
        return (Date) getValue(columnName);
    }

    @Override
    public LocalDate getLocalDate(String columnName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocalDateTime getLocalDateTime(String columnName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal getBigDecimal(String columnName) {
        return (BigDecimal) getValue(columnName);
    }

    @Override
    public String getString(String columnName) {
        return (String) getValue(columnName);
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
    public Boolean getBoolean(int index) {
        return (Boolean) getValue(index);
    }

    @Override
    public Byte getByte(int index) {
        return (Byte) getValue(index);
    }

    @Override
    public Short getShort(int index) {
        return (Short) getValue(index);
    }

    @Override
    public Integer getInteger(int index) {
        return (Integer) getValue(index);
    }

    @Override
    public Long getLong(int index) {
        return (Long) getValue(index);
    }

    @Override
    public Float getFloat(int index) {
        return (Float) getValue(index);
    }

    @Override
    public Double getDouble(int index) {
        return (Double) getValue(index);
    }

    @Override
    public Date getDate(int index) {
        return (Date) getValue(index);
    }

    @Override
    public LocalDate getLocalDate(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocalDateTime getLocalDateTime(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal getBigDecimal(int index) {
        return (BigDecimal) getValue(index);
    }

    @Override
    public String getString(int index) {
        return (String) getValue(index);
    }

    @Override
    public Object getObject(int index) {
        return getValue(index);
    }

    @Override
    public Map<String, Object> toMap() {
        if (map == null) {
            map = new LinkedHashMap<>();
        }
        return map;
    }

}
