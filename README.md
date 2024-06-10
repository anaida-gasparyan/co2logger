# Getting started
- Build the project and run tests with `./gradlew build`
- Run the application with `./gradlew bootRun`

# Technologies used
- Language: Kotlin
- Core framework: Spring Boot 3 with Spring Framework 6 Kotlin support
- Server: Apache Tomcat
- Persistence : Spring Data JPA
- Databases: H2
- Build: Gradle
- Testing: Junit 5, Mockk

### Assumptions
1. The status goes from `WARN` to `OK` after one sensor `measurement < 2000`
2. The sensor measurements arrive in chronological order
3. If the sensor doesn't have any measurements it's status is `OK`
4. If the sensor doesn't have any measurements it's metrics are `0`

### Further improvements
1. Add more tests + test data for integration tests
2. Rethink the database type 
   - maybe document database suits better to save sensor 
   - archiving of measurements older than 30days
   - partitioning
3. Caching
4. Improve controller validations
5. Do we need a table with all the sensors (uuid as an id)
6. Add profiles to application.yaml
7. Return 404 if the sensor data does not exist
8. Add docker support