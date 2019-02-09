# fluent-query
Fluent Jdbc Query API for Java

```java
Integer id = 1;
String username = FluentQuery.ofSql("SELECT username FROM user WHERE id = ?")
                    .param(id)
                    .query()
                    .map(String.class)
                    .getOne();
```
