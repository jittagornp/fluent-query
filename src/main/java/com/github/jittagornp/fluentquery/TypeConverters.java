/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author jitta
 */
public class TypeConverters {

    private static final Map<Class, TypeConverter> map = new HashMap<>();

    static {
        register(Boolean.class, new CastValueTypeConverter(Boolean.class));
        register(Short.class, new CastValueTypeConverter(Short.class));
        register(Integer.class, new CastValueTypeConverter(Integer.class));
        register(Long.class, new CastValueTypeConverter(Long.class));
        register(Float.class, new CastValueTypeConverter(Float.class));
        register(Double.class, new CastValueTypeConverter(Double.class));
        register(Date.class, new CastValueTypeConverter(Date.class));
        register(BigDecimal.class, new CastValueTypeConverter(BigDecimal.class));
        register(String.class, new CastValueTypeConverter(String.class));
        register(LocalDate.class, new LocalDateTypeConverter());
        register(LocalDateTime.class, new LocalDateTimeTypeConverter());
    }

    private TypeConverters() {

    }

    public static void register(Class typeClass, TypeConverter converter) {
        map.put(typeClass, converter);
    }

    public static TypeConverter get(Class key) {
        return map.get(key);
    }

    public static boolean contains(Class key) {
        return map.containsKey(key);
    }

    public static Set<Class> keys() {
        return Collections.unmodifiableSet(map.keySet());
    }
}
