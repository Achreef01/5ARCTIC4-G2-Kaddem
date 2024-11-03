# Use a base Java image
FROM openjdk:8

# Set environment variables for Nexus details
ENV NEXUS_URL=http://192.168.56.4:8081
ENV ARTIFACT_PATH=tn/esprit/spring/kaddem/maven-metadata.xml
ENV VERSION=1.0-SNAPSHOT

# Install necessary packages and download the .jar file from Nexus
RUN apt-get update && apt-get install -y wget curl \
    && mkdir -p /app \
    && SNAPSHOT_VERSION=$(curl -s "${NEXUS_URL}/repository/Kaddem-AchrefOCHI-5Arctic4/${ARTIFACT_PATH}/${VERSION}/maven-metadata.xml" | grep -oPm1 "(?<=<value>)[^<]+") \
    && wget ${NEXUS_URL}/repository/Kaddem-AchrefOCHI-5Arctic4/${ARTIFACT_PATH}/${VERSION}/kaddem-${SNAPSHOT_VERSION}.jar -O /app/kaddem.jar

# Set the entry point to run the jar
ENTRYPOINT ["java", "-jar", "/app/kaddem.jar"]
