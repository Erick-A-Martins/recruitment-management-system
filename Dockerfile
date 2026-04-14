FROM ubuntu:24.04 AS build

RUN apt-get update
RUN apt-get install openjdk-21-jdk maven -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM eclipse-temurin:21-jre-jammy
EXPOSE 8080

COPY --from=build /target/gestao_vagas-0.0.1.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]