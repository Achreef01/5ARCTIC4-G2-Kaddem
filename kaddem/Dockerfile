# Specify the base image
FROM openjdk:8-jre-alpine

# Define build arguments
ARG NEXUS_URL=http://192.168.56.4:8081
ARG ARTIFACT_PATH=tn/esprit/spring/kaddem/maven-metadata.xml
ARG VERSION=0.0.1-SNAPSHOT

# Install necessary packages and download the jar
RUN apk add --no-cache curl wget \
    && mkdir -p /app \
    && SNAPSHOT_VERSION=$(curl -s "${NEXUS_URL}/repository/maven-snapshots/${ARTIFACT_PATH}" | grep -o "<version>[^<]*" | sed 's/<version>//') \
    && echo "Found SNAPSHOT_VERSION: ${SNAPSHOT_VERSION}" \
    && wget "${NEXUS_URL}/repository/maven-snapshots/tn/esprit/spring/kaddem/${SNAPSHOT_VERSION}/kaddem-${SNAPSHOT_VERSION}.jar" -O /app/kaddem.jar

# Set the working directory
WORKDIR /app

# Command to run the jar file
CMD ["java", "-jar", "kaddem.jar"]
