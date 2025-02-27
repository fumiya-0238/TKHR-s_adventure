# ビルドステージ
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# 実行ステージ
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/tkhr-0.0.1-SNAPSHOT.jar app.jar
RUN echo "JAVA_PATH=$(find / -name java -type f 2>/dev/null || echo 'Java not found')"
RUN echo "JAVA_VERSION=$(/bin/sh -c 'java -version' 2>&1 || echo 'Java not executable')"
RUN ls -l /app/app.jar || echo "JAR file missing"
EXPOSE 8080
ENV PORT=8080
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT}"]