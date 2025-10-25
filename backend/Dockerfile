FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B -f pom.xml -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -B -f pom.xml -q -DskipTests package
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/codearena-backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]