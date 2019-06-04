FROM quay.io/quarkus/centos-quarkus-maven AS maven
COPY src /tmp/nostos-api/src
COPY pom.xml /tmp/nostos-api
RUN mvn -f /tmp/nostos-api -DskipTests clean package

FROM openjdk:11.0.3-slim as jlink
COPY --from=maven /tmp/nostos-api/target/*-runner.jar /tmp/nostos-api/nostos-api.jar
RUN jlink --no-header-files --no-man-pages --compress=2 --strip-debug --add-modules $(jdeps --print-module-deps /tmp/nostos-api/nostos-api.jar) --output /tmp/nostos-api/runtime

FROM ubuntu:latest
COPY --from=jlink /tmp/nostos-api/runtime /opt/nostos-api/runtime
COPY --from=maven /tmp/nostos-api/target/*-runner.jar /opt/nostos-api/nostos-api.jar
EXPOSE 8080
ENTRYPOINT ["/opt/nostos-api/runtime/bin/java", "-jar", "/opt/nostos-api/nostos-api.jar", "-Dquarkus.http.host=0.0.0.0"]