FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-focal
WORKDIR /app
RUN echo "JAVA_PATH=$(find / -name java -type f 2>/dev/null || echo 'Java not found')"
ENV PATH="/opt/java/openjdk-17/bin:${PATH}"
COPY --from=build /app/target/tkhr-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENV PORT=8080
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT}"]