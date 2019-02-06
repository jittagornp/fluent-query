/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

import java.sql.SQLException;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author jitta
 */
public class QueryTest {

    @Test
    public void test() throws SQLException {
        FluentQueryStatement statement = new FluentQueryStatement() {
            @Override
            public FluentQueryMapper query(TransactionContext context) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public FluentQueryMapper update(TransactionContext context) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public FluentQueryStatement of(String sql) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public FluentQueryStatement param(Object object) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        TransactionContext tx = null;

        ResultRow row = statement
                .of("SELECT * FROM ...")
                .param(null)
                .param(null)
                .query(tx)
                .map()
                .getOne();

        List<String> ids = statement
                .of("SELECT * FROM ...")
                .param(null)
                .query(tx)
                .map(r -> r.getString("id"))
                .getList();

        Integer count = statement
                .of("SELECT COUNT(*) FROM ...")
                .param(null)
                .query(tx)
                .map(Integer.class)
                .getOne();

        statement
                .of("UPDATE ...")
                .param(null)
                .param(null)
                .update(tx);
    }

}
