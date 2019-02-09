# fluent-query
Fluent Jdbc Query API for Java


# How to 

### Simple 
```java
Integer id = 1;
String username = FluentQuery.ofSql("SELECT username FROM user WHERE id = ?")
                    .param(id)
                    .query()
                    .map(String.class)
                    .getOne();
```
### Map to Class 
```java
Integer id = 1;
User user = FluentQuery.ofSql("SELECT * FROM user WHERE id = ?")
              .param(id)
              .query()
              .map(User.class)
              .getOne();
```

### Get List 
``` java
List<String> domains = FluentQuery.ofSql("SELECT domain_name FROM oauth2_allow_domain")
                        .query()
                        .map(String.class)
                        .getList();
```

### Transaction 
``` java
defineTx(tx -> {
    
    ...
    ...
   
    FluentQuery.ofSql("UPDATE user SET name = ? WHERE id = ?")
      .param("Jiattgorn")
      .param(1)
      .update(tx);
    
});
```
