/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

import java.sql.ResultSet;

/**
 *
 * @author jitta
 */
public interface ResultSetMapper<T> {

    T map(ResultSet resultSet);

}
