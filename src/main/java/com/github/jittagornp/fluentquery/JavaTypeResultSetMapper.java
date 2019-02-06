/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jitta
 * @param <T>
 */
public class JavaTypeResultSetMapper<T> implements ResultSetMapper<T> {

    private final Class<T> typeClass;

    public JavaTypeResultSetMapper(Class<T> typeClass) {
        this.typeClass = typeClass;
    }

    @Override
    public T map(ResultSet resultSet) {
        try {
            if (resultSet == null) {
                return null;
            }
            return typeClass.cast(resultSet.getObject(1));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
