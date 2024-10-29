FROM openjdk:11
EXPOSE 8085
WORKDIR /app

RUN apt-get update && apt-get install -y curl


RUN curl -o kaddem.jar -L "http://192.168.33.10:8081/repository/maven-releases/tn/esprit/spring/kaddem/0.0.1-SNAPSHOT/kaddem-0.0.1-SNAPSHOT.jar"

ENTRYPOINT ["java", "-jar", "kaddem.jar"]
