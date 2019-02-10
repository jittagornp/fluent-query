# fluent-query
Fluent Jdbc Query API for Java

# Configuration
 
> classpath : /application.properties

use Hikari CP for manage Database Connection Pool  
https://github.com/brettwooldridge/HikariCP

# How to 

### getOne 
```java
ResultRow row = FluentQuery.ofSql("SELECT id FROM user WHERE username = ?")
                            .param("test")
                            .query()
                            .map()
                            .getOne();
LOG.debug("id => {}", row.getString("id"));
```
### getOneMapClass
```java
String id = FluentQuery.ofSql("SELECT id FROM user WHERE username = ?")
                        .param("test")
                        .query()
                        .map(String.class)
                        .getOne();
LOG.debug("id => {}", id);
```

### getList 
``` java
List<ResultRow> rows = FluentQuery.ofSql("SELECT domain_name FROM oauth2_allow_domain")
                                  .query()
                                  .map()
                                  .getList();
rows.stream().forEach(r -> {
    LOG.debug("domain => {}", r.getString("domain_name"));
});
```

### getListMapClass
``` java
List<String> domains = FluentQuery.ofSql("SELECT domain_name FROM oauth2_allow_domain")
                                  .query()
                                  .map(String.class)
                                  .getList();
domains.stream().forEach(domain -> {
    LOG.debug("domain => {}", domain);
});
```

### mapObjectClass
``` java
public class User {

    @ColumnMapping("ID")
    private String id;

    @ColumnMapping("USERNAME")
    private String username;

    @ColumnMapping("PASSWORD")
    private String password;

    @ColumnMapping("CREATED_DATE")
    private LocalDateTime createdDate;

    @ColumnMapping("CREATED_USER")
    private String createdUser;
    
    //getter and setter 
}

User user = FluentQuery.ofSql("SELECT * FROM user WHERE username = ?")
                        .param("test")
                        .query()
                        .map(User.class)
                        .getOne();
LOG.debug("user => {}", user);
```

### transaction
``` java
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
```
