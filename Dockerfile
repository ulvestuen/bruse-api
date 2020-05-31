# When docker image is built from this file, the image bundles the latest version of Java, which is available under
# a "???" license. For details, see https://????
FROM maven:latest as builder
RUN groupadd -g 999 bruse-api && \
    useradd -r -u 999 -g bruse-api bruse-api

ADD --chown=bruse-api:bruse-api ./ /home/bruse-api/
WORKDIR /home/bruse-api
USER bruse-api
RUN mvn clean install



FROM openjdk:11-jre-slim
RUN groupadd -g 999 bruse-api && \
    useradd -r -u 999 -g bruse-api bruse-api

COPY --from=builder /home/bruse-api/target/bruse-api.jar /home/bruse-api/bruse-api.jar
WORKDIR /home/bruse-api

USER bruse-api
EXPOSE 8080
ENTRYPOINT ["java","-jar", "bruse-api.jar"]
