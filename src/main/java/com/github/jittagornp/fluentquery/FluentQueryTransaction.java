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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jitta
 */
public class FluentQueryTransaction {

    private static final Logger LOG = LoggerFactory.getLogger(FluentQueryTransaction.class);

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
    
    private static String randomId(){
        return UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8)
                .toUpperCase();
    }

    public static <T> T defineTx(TransactionCallback<T> callback) {
        final String txId = randomId();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            LOG.debug("[{}] : open connection...", txId);
            connection.setAutoCommit(false);
            T returnValue = callback.execute(new TransactionContextImpl(txId, connection));
            connection.commit();
            LOG.debug("[{}] : commit...", txId);
            return returnValue;
        } catch (SQLException ex) {
            if (connection != null) {
                try {
                    connection.rollback();
                    LOG.debug("[{}] : rollback...", txId);
                } catch (SQLException e) {
                    throw new FluentQuerySQLException(e);
                }
            }
            throw new FluentQuerySQLException(ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    LOG.debug("[{}] : close connection...", txId);
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

    private static class TransactionContextImpl implements TransactionContext {

        private final String txId;

        private final Connection connection;

        public TransactionContextImpl(String txId, Connection connection) {
            this.txId = txId;
            this.connection = connection;
        }

        @Override
        public String getTxId() {
            return txId;
        }

        @Override
        public Connection getConnection() {
            return connection;
        }
    }

}
