FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
ARG JAR_FILE
ADD build/libs/microservices-messaging-producer-0.0.1-SNAPSHOT.jar microservices-messaging-producer.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","microservices-messaging-producer.jar"]