/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author jitta
 */
public class FluentQueryMapperImpl implements FluentQueryMapper {

    private static final List<String> JAVA_TYPES = Arrays.asList(
            Boolean.class.getName(),
            Short.class.getName(),
            Integer.class.getName(),
            Long.class.getName(),
            Float.class.getName(),
            Double.class.getName(),
            Date.class.getName(),
            LocalDate.class.getName(),
            LocalDateTime.class.getName(),
            BigDecimal.class.getName(),
            String.class.getName()
    );

    private final ResultSet resultSet;

    public FluentQueryMapperImpl(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    private <T> ResultSetMapper<T> getResultSetMapper(Class<T> clazz) {
        boolean isJavaType = JAVA_TYPES.contains(clazz.getName());
        if (isJavaType) {
            return new JavaTypeResultSetMapper<>(clazz);
        }
        return new BeanResultSetMapper<>(clazz);
    }

    private <T> List<T> fetchResultSet(Class<T> clazz) throws SQLException {
        try {
            ResultSetMapper<T> mapper = getResultSetMapper(clazz);
            List<T> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(mapper.map(resultSet));
            }
            return list;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    @Override
    public <T> FluentQueryGetter<T> map(Class<T> clazz) throws SQLException {
        return new FluentQueryGetterImpl<>(fetchResultSet(clazz));
    }

    @Override
    public <T> FluentQueryGetter map(RowMapperCallback<T> callback) throws SQLException {
        return new FluentQueryGetterImpl<>(
                fetchResultSet(ResultRow.class)
                        .stream()
                        .map(row -> callback.map(row))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public FluentQueryGetter map() throws SQLException {
        return map(ResultRow.class);
    }

}
