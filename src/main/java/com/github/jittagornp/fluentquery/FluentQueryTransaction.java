/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

/**
 *
 * @author jitta
 */
public class FluentQueryTransaction {

    private static HikariDataSource dataSource = createDataSource();

    private FluentQueryTransaction() {

    }

    //https://github.com/brettwooldridge/HikariCP
    private static synchronized HikariDataSource createDataSource() {
        if (dataSource == null) {
            HikariConfig config = new HikariConfig("/application.properties");
            dataSource = new HikariDataSource(config);
        }
        return dataSource;
    }

    public static <T> T defineTx(TransactionCallback<T> callback) {
        final String txId = UUID.randomUUID().toString();
        Connection connection = null;
        try {
            return callback.execute(new TransactionContext() {
                @Override
                public String getTxId() {
                    return txId;
                }

                @Override
                public Connection getConnection() {
                    try {
                        return dataSource.getConnection();
                    } catch (SQLException ex) {
                        throw new FluentQuerySQLException(ex);
                    }
                }
            });
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    throw new FluentQuerySQLException(ex);
                }
            }
        }
    }

    public static void defineTx(final TransactionCallbackWithoutReturn callback) {
        defineTx(context -> {
            callback.execute(context);
            return null;
        });
    }

}
