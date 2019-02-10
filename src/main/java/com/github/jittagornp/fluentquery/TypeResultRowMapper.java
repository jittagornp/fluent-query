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
public class TypeResultRowMapper<T> implements ResultRowMapper<T> {

    private final Class<T> typeClass;

    public TypeResultRowMapper(Class<T> typeClass) {
        this.typeClass = typeClass;
    }

    @Override
    public T map(ResultRow resultRow) {
        if(resultRow == null){
            return null;
        }
        return resultRow.get(0, typeClass);
    }

}
