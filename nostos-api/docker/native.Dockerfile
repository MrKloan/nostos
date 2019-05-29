FROM quay.io/quarkus/centos-quarkus-maven AS builder
COPY src /usr/nostos-api/src
COPY pom.xml /usr/nostos-api
RUN mvn -f /usr/nostos-api -DskipTests -Pnative clean package

FROM registry.fedoraproject.org/fedora-minimal
COPY --from=builder /usr/nostos-api/target/*-runner /nostos-api
EXPOSE 8080
ENTRYPOINT ["./nostos-api", "-Dquarkus.http.host=0.0.0.0"]