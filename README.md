# Chat App (Whomp Chat?)

A Spring Boot api to provide endpoints for a real-time messaging platform

### Installation:

Build with maven 
``` mvn package ```

Set enviroment variable for profile to either "in-memory" or "cloud"

How to export enviroment variables on Unix machines:
``` export spring_profil:in-memory ```

If profile is set to cloud then set the database url, user, and password with the following enviorment variables:
```
spring_datasource_url
spring_datasource_username
spring_datasource_password
```

run the packaged jar
