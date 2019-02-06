/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

/**
 *
 * @author jitta
 */
public class StringUtils {

    private StringUtils() {

    }

    public static boolean hasText(String text) {
        return !(text == null || text.isEmpty());
    }

}
