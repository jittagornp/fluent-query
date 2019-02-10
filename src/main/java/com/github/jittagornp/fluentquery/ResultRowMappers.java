/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author jitta
 */
public class ResultRowMappers {

    private static final Map<Class, ResultRowMapper> map = new HashMap<>();

    static {
        TypeConverters.keys().forEach(typeClass -> {
            register(typeClass, new CastValueResultRowMapper(typeClass));
        });
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

    public static Set<Class> keys() {
        return Collections.unmodifiableSet(map.keySet());
    }
}
