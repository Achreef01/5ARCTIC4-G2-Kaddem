FROM openjdk:11
EXPOSE 8085
WORKDIR /app


RUN apt-get update && apt-get install -y curl


RUN curl -o Kaddem-latest.jar -L "http://192.168.33.10:8081/repository/maven-releases/com/esprit/Kaddem/latest/Kaddem-latest.jar"


ENTRYPOINT ["java", "-jar", "Kaddem-latest.jar"]
