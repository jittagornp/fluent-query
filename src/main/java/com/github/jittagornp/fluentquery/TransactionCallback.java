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
public interface TransactionCallback<T> {
    
    T execute(TransactionContext context);
    
}
