# Build stage
FROM maven:3.8.3-openjdk-17 AS build
COPY src/ /home/app/src
COPY pom.xml /home/app/pom.xml
WORKDIR /home/app
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /home/app/target/*.jar inventory-service.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "/inventory-service.jar"]
