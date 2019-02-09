/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

/**
 *
 * @author jitta
 * @param <T>
 */
public class CastValueResultRowMapper<T> implements ResultRowMapper<T> {

    private final Class<T> typeClass;

    public CastValueResultRowMapper(Class<T> typeClass) {
        this.typeClass = typeClass;
    }

    @Override
    public T map(ResultRow resultRow) {
        if (resultRow == null) {
            return null;
        }
        return typeClass.cast(resultRow.getObject(0));
    }

}
