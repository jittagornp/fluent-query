/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

import static com.github.jittagornp.fluentquery.FluentQueryTransaction.defineTx;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jitta
 */
public class FluentQueryIT {

    private static final Logger LOG = LoggerFactory.getLogger(FluentQueryIT.class);

    @Test
    public void getOne() throws SQLException {
        ResultRow row = FluentQuery.ofSql("SELECT id FROM user WHERE username = ?")
                .param("test")
                .query()
                .map()
                .getOne();
        LOG.debug("id => {}", row.getString("id"));
    }

    @Test
    public void getOneMapClass() throws SQLException {
        String id = FluentQuery.ofSql("SELECT id FROM user WHERE username = ?")
                .param("test")
                .query()
                .map(String.class)
                .getOne();
        LOG.debug("id => {}", id);
    }

    @Test
    public void getList() throws SQLException {
        List<ResultRow> rows = FluentQuery.ofSql("SELECT domain_name FROM oauth2_allow_domain")
                .query()
                .map()
                .getList();
        rows.stream().forEach(r -> {
            System.out.println(r.getString("domain_name"));
        });
    }

    @Test
    public void getListMapClass() throws SQLException {
        List<String> domains = FluentQuery.ofSql("SELECT domain_name FROM oauth2_allow_domain")
                .query()
                .map(String.class)
                .getList();
        domains.stream().forEach(domain -> {
            System.out.println(domain);
        });
    }

    @Test
    public void mapObjectClass() throws SQLException {
        User user = FluentQuery.ofSql("SELECT * FROM user WHERE username = ?")
                .param("test")
                .query()
                .map(User.class)
                .getOne();
        System.out.println(user);
    }
    
    @Test
    public void transaction(){
        defineTx(tx -> {
            
            FluentQuery.ofSql("UPDATE user set updated_date = ?, updated_user = ? WHERE username = ?")
                    .param(LocalDateTime.now())
                    .param("jittagornp")
                    .param("test1")
                    .update(tx);
            
            FluentQuery.ofSql("UPDATE user set updated_date = ?, updated_user = ? WHERE username = ?")
                    .param(LocalDateTime.now())
                    .param("jittagornp")
                    .param("test2")
                    .update(tx);
            
        });
    }
}
