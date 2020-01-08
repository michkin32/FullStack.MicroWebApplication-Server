# Chat App (Whomp Chat?)

A Spring Boot api to provide endpoints for a real-time messaging platform

### Requirements:
#### In-Memory/Cloud:
- Java 8+ 
- Maven
#### Cloud: 
- Mariadb

### Installation:

1. Build with maven: ``` mvn package ```

2. Set enviroment variable for profile to either "in-memory" or "cloud"

- Command to set the profile to in-memory on Unix machines:``` export spring_profiles_active=in-memory ```
- Command to set the profile to cloud:``` export spring_profiles_active=cloud ```

3. If profile is set to cloud then set the database url, user, and password using the following enviorment variables:
```
spring_datasource_url
spring_datasource_username
spring_datasource_password
```

4. run the packaged jar: ```java -jar build_name.jar```
