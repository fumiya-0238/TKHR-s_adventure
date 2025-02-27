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
RUN find / -name java -type f 2>/dev/null || echo "Java not found"
RUN java -version || echo "Java version not available"
EXPOSE 8080
ENV PORT=8080
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT}"]