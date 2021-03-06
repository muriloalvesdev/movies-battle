FROM adoptopenjdk/openjdk11:alpine
VOLUME /tmp
EXPOSE 8081
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
ADD target/moviesbattle-0.0.1-SNAPSHOT.jar /app/moviesbattle.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=prod", "-jar", "/app/moviesbattle.jar"]
