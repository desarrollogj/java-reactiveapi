# Java Reactive Api

An Api using Java 11, Spring and the following packages:

- Springboot starter webflux
- In-memory database (h2)
- Log4j2
- Lombok
- Mapstruct
- Springfox (Swagger)
- Junit 5 tests and Jacoco plugin for coverage

### Api documentation

To run, you can use Maven:

`mvn spring-boot:run -Dspring-boot.run.profiles=dev`

After run, visit:

[http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/)

To run the test, use:

`mvn test`

You will find the coverage report visiting `/target/site/jacoco` directory.
