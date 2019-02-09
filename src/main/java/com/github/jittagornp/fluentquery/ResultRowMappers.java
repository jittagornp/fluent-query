/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jitta
 */
public class ResultRowMappers {

    private static final Map<Class, ResultRowMapper> map = new HashMap<>();

    static {
        register(Boolean.class, new CastValueResultRowMapper(Boolean.class));
        register(Short.class, new CastValueResultRowMapper(Short.class));
        register(Integer.class, new CastValueResultRowMapper(Integer.class));
        register(Long.class, new CastValueResultRowMapper(Long.class));
        register(Float.class, new CastValueResultRowMapper(Float.class));
        register(Double.class, new CastValueResultRowMapper(Double.class));
        register(Date.class, new CastValueResultRowMapper(Date.class));
        register(BigDecimal.class, new CastValueResultRowMapper(BigDecimal.class));
        register(String.class, new CastValueResultRowMapper(String.class));
        register(Map.class, new MapResultRowMapper());
    }

    private ResultRowMappers() {

    }

    public static void register(Class typeClass, ResultRowMapper mapper) {
        map.put(typeClass, mapper);
    }

    public static ResultRowMapper get(Class key) {
        return map.get(key);
    }

    public static boolean contains(Class key) {
        return map.containsKey(key);
    }
}
