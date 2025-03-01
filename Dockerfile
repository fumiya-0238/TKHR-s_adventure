FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
#テスト -DskipTests

FROM eclipse-temurin:17-jdk-focal
WORKDIR /app
COPY --from=build /app/target/tkhr-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENV PORT=8080
ENTRYPOINT ["/opt/java/openjdk/bin/java", "-jar", "app.jar", "--server.port=${PORT}"]