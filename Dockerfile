FROM openjdk:11
VOLUME /tmp
ADD .target/conexiona-back-0.0.1-SNAPSHOT.jar backend.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "backend.jar"]
