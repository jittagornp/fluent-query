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
public interface FluentQueryMapper {

    <T> FluentQueryGetter<T> map(Class<T> clazz);

    <T> FluentQueryGetter<T> map(RowMapperCallback<T> callback);

    FluentQueryGetter<ResultRow> map();

}
