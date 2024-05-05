FROM maven:3.8.1-openjdk-17-slim AS build

COPY pom.xml /usr/src/app/

RUN mvn -f /usr/src/app/pom.xml dependency:go-offline -B

COPY src /usr/src/app/src

RUN mvn -f /usr/src/app/pom.xml package

FROM openjdk:17-jre-slim

COPY --from=build /usr/src/app/target/*.jar /usr/app/app.jar

CMD ["java", "-jar", "/usr/app/app.jar"]