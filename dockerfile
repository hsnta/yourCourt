FROM openjdk:17  as build
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod +x mvnw && ./mvnw clean package -DskipTests

FROM openjdk:17

WORKDIR /app
COPY --from=build /app/target/basketballService-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "basketballService-0.0.1-SNAPSHOT.jar"]