# Chat App (Whomp Chat?)

A Spring Boot api to provide endpoints for a real-time messaging platform

### Installation:

1. Build with maven: ``` mvn package ```

2. Set enviroment variable for profile to either "in-memory" or "cloud"

- Command to set the profile on Unix machines:``` export spring_profil:in-memory ```

3. If profile is set to cloud then set the database url, user, and password using the following enviorment variables:
```
spring_datasource_url
spring_datasource_username
spring_datasource_password
```

4. run the packaged jar
