package com.github.jittagornp.fluentquery;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jitta
 */
public class CastValueTypeConverter<T> implements TypeConverter<T> {

    private final Class<T> typeClass;

    public CastValueTypeConverter(Class<T> typeClass) {
        this.typeClass = typeClass;
    }

    @Override
    public T convert(Object value) {
        if (value == null) {
            return null;
        }
        return typeClass.cast(value);
    }

}
