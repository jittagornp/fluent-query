/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

import java.util.Collection;

/**
 *
 * @author jitta
 */
public class CollectionUtils {

    private CollectionUtils() {

    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

}
