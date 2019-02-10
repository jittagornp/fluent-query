/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author jitta
 */
public class FluentQueryMapperImpl implements FluentQueryMapper {

    private List<ResultRow> resultRows;

    public FluentQueryMapperImpl(List<ResultRow> resultRows) {
        this.resultRows = resultRows;
    }

    private List<ResultRow> getResultRows() {
        if (resultRows == null) {
            resultRows = new ArrayList<>();
        }
        return resultRows;
    }

    private <T> ResultRowMapper<T> getResultRowMapper(Class<T> typeClass) {
        if (ResultRowMappers.contains(typeClass)) {
            return ResultRowMappers.get(typeClass);
        }
        return new BeanResultRowMapper<>(typeClass);
    }

    @Override
    public <T> FluentQueryGetter<T> map(Class<T> typeClass) {
        if(getResultRows().isEmpty()){
            return FluentQueryGetterImpl.empty();
        }
        ResultRowMapper<T> mapper = getResultRowMapper(typeClass);
        return new FluentQueryGetterImpl<>(
                getResultRows()
                        .stream()
                        .map(r -> mapper.map(r))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public <T> FluentQueryGetter<T> map(RowMapperCallback<T> callback) {
        if(getResultRows().isEmpty()){
            return FluentQueryGetterImpl.empty();
        }
        return new FluentQueryGetterImpl<>(
                getResultRows()
                        .stream()
                        .map(row -> callback.map(row))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public FluentQueryGetter<ResultRow> map() {
        return new FluentQueryGetterImpl<>(getResultRows());
    }

}
