/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

import java.sql.SQLException;

/**
 *
 * @author jitta
 */
public interface FluentQueryStatement {
    
    FluentQueryStatement of(String sql);
    
    FluentQueryStatement param(Object object);
    
    FluentQueryMapper query(TransactionContext context) throws SQLException;
    
    FluentQueryMapper update(TransactionContext context) throws SQLException;
    
}
