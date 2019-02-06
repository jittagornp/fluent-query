/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

import static com.github.jittagornp.fluentquery.CollectionUtils.isEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jitta
 */
public class FluentQueryGetterImpl<T> implements FluentQueryGetter<T> {

    private final List<T> rows;

    public FluentQueryGetterImpl(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public T getOne() {
        if (isEmpty(rows)) {
            return null;
        }
        return rows.get(0);
    }

    @Override
    public List<T> getList() {
        if (isEmpty(rows)) {
            return new ArrayList<>();
        }
        return rows;
    }

}
