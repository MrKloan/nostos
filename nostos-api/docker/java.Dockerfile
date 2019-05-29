FROM quay.io/quarkus/centos-quarkus-maven AS builder
COPY src /usr/nostos-api/src
COPY pom.xml /usr/nostos-api
RUN mvn -f /usr/nostos-api -DskipTests clean package

FROM openjdk:11.0.3-slim
COPY --from=builder /usr/nostos-api/target/*-runner.jar /nostos-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/nostos-api.jar", "-Dquarkus.http.host=0.0.0.0"]