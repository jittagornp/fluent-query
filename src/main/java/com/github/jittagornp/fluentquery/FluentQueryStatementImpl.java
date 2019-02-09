/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

import static com.github.jittagornp.fluentquery.FluentQueryTransaction.defineTx;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jitta
 */
public class FluentQueryStatementImpl implements FluentQueryStatement {

    private static final Logger LOG = LoggerFactory.getLogger(FluentQueryStatementImpl.class);

    private final String sql;

    private List<Object> params;

    public FluentQueryStatementImpl(String sql) {
        this.sql = sql;
    }

    private List<Object> getParams() {
        if (params == null) {
            params = new ArrayList<>();
        }
        return params;
    }

    @Override
    public FluentQueryStatement param(Object object) {
        getParams().add(object);
        return this;
    }

    private void setParams(PreparedStatement statement) throws SQLException {
        List<Object> ps = getParams();
        for (int i = 0; i < ps.size(); i++) {
            statement.setObject(i + 1, ps.get(i));
        }
    }

    @Override
    public FluentQueryMapper query() {
        return defineTx((TransactionContext context) -> query(context));
    }

    @Override
    public FluentQueryMapper query(TransactionContext context) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            LOG.debug("sql => {}", sql);
            Connection connection = context.getConnection();
            statement = connection.prepareStatement(sql);
            setParams(statement);
            resultSet = statement.executeQuery();
            return new FluentQueryMapperImpl(fatch(resultSet));
        } catch (SQLException ex) {
            throw new FluentQuerySQLException(ex);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    throw new FluentQuerySQLException(ex);
                }
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    throw new FluentQuerySQLException(ex);
                }
            }
        }
    }

    @Override
    public FluentQueryMapper execute() {
        return defineTx((TransactionContext context) -> execute(context));
    }

    @Override
    public FluentQueryMapper execute(TransactionContext context) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Connection connection = context.getConnection();
            statement = connection.prepareStatement(sql);
            setParams(statement);
            statement.execute();
            resultSet = statement.getResultSet();
            return new FluentQueryMapperImpl(fatch(resultSet));
        } catch (SQLException ex) {
            throw new FluentQuerySQLException(ex);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    throw new FluentQuerySQLException(ex);
                }
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    throw new FluentQuerySQLException(ex);
                }
            }
        }
    }

    @Override
    public FluentQueryMapper update() {
        return defineTx((TransactionContext context) -> update(context));
    }

    @Override
    public FluentQueryMapper update(TransactionContext context) {
        PreparedStatement statement = null;
        try {
            Connection connection = context.getConnection();
            statement = connection.prepareStatement(sql);
            setParams(statement);
            statement.executeUpdate();
            return new FluentQueryMapperImpl(null);
        } catch (SQLException ex) {
            throw new FluentQuerySQLException(ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    throw new FluentQuerySQLException(ex);
                }
            }
        }
    }

    private List<ResultRow> fatch(ResultSet resultSet) throws SQLException {
        List<ResultRow> rows = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()) {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object columnValue = resultSet.getObject(i);
                map.put(columnName.toUpperCase(), columnValue);
            }
            rows.add(new ResultRowImpl(map));
        }
        return rows;
    }

}
