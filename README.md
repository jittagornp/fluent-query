# fluent-query
Fluent Jdbc Query API for Java

# Configuration
 
> classpath : /application.properties

use Hikari CP for manage Database Connection Pool  
https://github.com/brettwooldridge/HikariCP

# How to 

### getOne 
```java
String sql = "SELECT id FROM user WHERE username = ?";
ResultRow row = FluentQuery.of(sql)
                        .param("test")
                        .query()
                        .map()
                        .getOne();
LOG.debug("id => {}", row.getString("id"));
```
### getOneMapClass
```java
String sql = "SELECT id FROM user WHERE username = ?";
String id = FluentQuery.of(sql)
                    .param("test")
                    .query()
                    .map(String.class)
                    .getOne();
LOG.debug("id => {}", id);
```

### getList 
``` java
String sql = "SELECT domain_name FROM oauth2_allow_domain";
List<ResultRow> rows = FluentQuery.of(sql)
                               .query()
                               .map()
                               .getList();
rows.stream().forEach(r -> {
    System.out.println(r.getString("domain_name"));
});
```

### getListMapClass
``` java
String sql = "SELECT domain_name FROM oauth2_allow_domain";
List<String> domains = FluentQuery.of(sql)
                               .query()
                               .map(String.class)
                               .getList();
domains.stream().forEach(domain -> {
    System.out.println(domain);
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

String sql = "SELECT * FROM user WHERE username = ?";
User user = FluentQuery.of(sql)
                    .param("test")
                    .query()
                    .map(User.class)
                    .getOne();
System.out.println(user);
```

### transaction
``` java
defineTx(tx -> {

    String sql = "UPDATE user set updated_date = ?, updated_user = ? WHERE username = ?";

    FluentQuery.of(sql)
            .param(LocalDateTime.now())
            .param("jittagornp")
            .param("test1")
            .update(tx);

    FluentQuery.of(sql)
            .param(LocalDateTime.now())
            .param("jittagornp")
            .param("test2")
            .update(tx);

});
```
