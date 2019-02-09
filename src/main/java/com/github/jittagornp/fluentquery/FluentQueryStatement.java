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
public interface FluentQueryStatement {

    FluentQueryStatement param(Object object);

    FluentQueryMapper query(TransactionContext context);

    FluentQueryMapper execute(TransactionContext context);

    FluentQueryMapper update(TransactionContext context);

}
