# Build stage
FROM maven:3.8.3-openjdk-17 AS build
COPY src/ /home/app/src
COPY pom.xml /home/app/pom.xml
WORKDIR /home/app
RUN mvn clean package -DskipTests

# Runtime stage
FROM tomcat:9-jdk17
COPY --from=build /home/app/target/*.war /usr/local/tomcat/webapps/apigateway-service.war
EXPOSE 80
CMD ["catalina.sh", "run"]