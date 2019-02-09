/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

import java.util.Map;

/**
 *
 * @author jitta
 */
public class MapResultRowMapper implements ResultRowMapper<Map> {

    @Override
    public Map<String, Object> map(ResultRow resultRow) {
        if (resultRow == null) {
            return null;
        }
        return resultRow.toMap();
    }

}
