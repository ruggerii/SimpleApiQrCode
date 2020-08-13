FROM openjdk:11-jdk-slim
VOLUME /tmp
ADD target/api-qr-generator-0.0.1-SNAPSHOT.jar api-generator.jar
EXPOSE 8079
RUN bash -c 'touch /api-generator.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/api-generator.jar"]