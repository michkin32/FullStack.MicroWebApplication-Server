# Chat App (Whomp Chat?)

A Spring Boot api to provide endpoints for a real-time messaging platform

### Instalation:

Build with maven 
``` mvn package ```

Set enviorment variable for profile to either "in-memory" or "cloud"

How to export enviorment variables on Unix machines:
``` export spring_profil:in-memory ```

If profile is set to cloud then set the database url, user, and password with the following enviorment variables:
```
spring_datasource_url
spring_datasource_username
spring_datasource_password
```

run the packaged jar
