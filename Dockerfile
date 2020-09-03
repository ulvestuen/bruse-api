# When docker image is built from this file, the image bundles a version of OpenJDK, which is available under
# licensing specified by "GNU General Public License, version 2, with the Classpath Exception".
# For details, see http://openjdk.java.net/legal/gplv2+ce.html

# When docker image is built from this file, the image bundles a version of Spring Boot, which is available under
# a "Apache License 2.0" license. For details, see https://github.com/spring-projects/spring-boot/blob/master/LICENSE.txt

# When docker image is built from this file, the image bundles a version of gson, which is available under
# a "Apache License 2.0" license. For details, see https://github.com/google/gson/blob/master/LICENSE

# When docker image is built from this file, the image bundles a version of junit, which is available under
# a "Eclipse Public License 1.0" license. For details, see https://junit.org/junit4/license.html

# When docker image is built from this file, the image bundles a version of jackson-databind-nullable,
# which is available under a "Apache License 2.0" license. For details, see https://github.com/OpenAPITools/jackson-databind-nullable/blob/master/LICENSE

# When docker image is built from this file, the image bundles a version of swagger-annotations,
# which is available under a "Apache License 2.0" license. For details, see https://swagger.io/license/

# When docker image is built from this file, the image bundles a version of aws-java-sdk-dynamodb,
# which is available under a "Apache License 2.0" license. For details, see https://github.com/aws/aws-sdk-java/blob/master/LICENSE.txt

# When docker image is built from this file, a version of maven is used in the build process. This tool
# is available under a "Apache License 2.0" license. For details, see http://maven.apache.org/ref/3.0/license.html

# When docker image is built from this file, a version of versions-maven-plugin is used in the build process. This tool
# is available under a "Apache License 2.0" license. For details, see https://www.mojohaus.org/versions-maven-plugin/license.html

# When docker image is built from this file, a version of openapi-generator-maven-plugin is used in the build process. This tool
# is available under a "Apache License 2.0" license. For details, see https://github.com/OpenAPITools/openapi-generator/blob/master/LICENSE

FROM maven:3.6.3-openjdk-11-slim as builder
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
