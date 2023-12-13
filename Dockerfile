FROM openjdk:21
LABEL maintainer="juaneng95"
ADD target/superheroes-1.0.0-SNAPSHOT.jar superheroes-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "superheroes-1.0.0-SNAPSHOT.jar"]