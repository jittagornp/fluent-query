/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

import static com.github.jittagornp.fluentquery.FluentQueryTransaction.defineTx;
import java.sql.SQLException;
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
    public void test() throws SQLException {
        defineTx(tx -> {
            User user = FluentQuery.ofSql("SELECT * FROM user WHERE id = ?")
                    .param("00000000000000000000000000000000")
                    .query(tx)
                    .map(User.class)
                    .getOne();
            LOG.debug("user => {}", user);
            
            String username = FluentQuery.ofSql("SELECT username FROM user WHERE id = ?")
                    .param("00000000000000000000000000000000")
                    .query(tx)
                    .map(String.class)
                    .getOne();
            
            LOG.debug("username => {}", username);
            
            List<String> domains = FluentQuery.ofSql("SELECT domain_name FROM oauth2_allow_domain")
                    .query(tx)
                    .map(String.class)
                    .getList();
            domains.stream().forEach(d -> LOG.debug("domain => {}", d));
        });
    }

}
