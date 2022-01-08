FROM openjdk:11
ADD .target/conexiona-back-0.0.1-SNAPSHOT.jar conexiona-back-0.0.1-SNAPSHOT.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "conexiona-back-0.0.1-SNAPSHOT.jar"]