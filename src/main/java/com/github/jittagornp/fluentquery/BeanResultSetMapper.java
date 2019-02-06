/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

import static com.github.jittagornp.fluentquery.StringUtils.hasText;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author jitta
 */
public class BeanResultSetMapper<T> implements ResultSetMapper<T> {

    private final Class<T> typeClass;

    private final List<Field> fields;

    public BeanResultSetMapper(Class<T> typeClass) {
        this.typeClass = typeClass;
        this.fields = getAllFields(typeClass);
    }

    private List<Field> getAllFields(Class<T> typeClass) {
        Class current = typeClass;
        List<Field> list = new ArrayList<>();
        while (current != Object.class) {
            list.addAll(Arrays.asList(current.getDeclaredFields()));
            current = current.getSuperclass();
        }
        return list;
    }

    private Object trimIfString(Object object) {
        if (object instanceof String) {
            return ((String) object).trim();
        }
        return object;
    }

    private boolean matches(Field field, String columnName) {
        if (field.isAnnotationPresent(ColumnMapping.class)) {
            ColumnMapping annotation = field.getAnnotation(ColumnMapping.class);
            String fieldName = hasText(annotation.value()) ? annotation.value() : field.getName();
            return columnName.equalsIgnoreCase(fieldName);
        } else {
            return columnName.equalsIgnoreCase(field.getName());
        }
    }

    private Field getFieldOfColumnName(List<Field> fields, String columnName) {
        Iterator<Field> iterator = fields.iterator();
        while (iterator.hasNext()) {
            Field field = iterator.next();
            if (matches(field, columnName)) {
                iterator.remove();
                return field;
            }
        }
        return null;
    }

    //x => setx
    //aStart => setaStart
    //fullName => setFullName 
    private String toSetterMethodName(String fieldName) {
        String name;
        if (fieldName.length() == 1) {
            name = fieldName;
        } else if (("" + fieldName.charAt(1)).matches("^[A-Z]+$")) {
            name = fieldName;
        } else {
            name = ("" + fieldName.charAt(0)).toUpperCase()
                    + fieldName.substring(1);
        }
        return "set" + name;
    }

    @Override
    public T map(ResultSet resultSet) {
        if (resultSet == null) {
            return null;
        }
        try {
            T instance = typeClass.newInstance();
            List<Field> fs = new ArrayList<>(fields);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object columnValue = trimIfString(resultSet.getObject(columnName));
                Field field = getFieldOfColumnName(fs, columnName);
                if (field != null) {
                    Method method = typeClass.getMethod(toSetterMethodName(columnName), field.getType());
                    method.invoke(instance, columnValue);
                }
            }
            return instance;
        } catch (InstantiationException | IllegalAccessException | SQLException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }
    }

}
