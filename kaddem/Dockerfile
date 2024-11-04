FROM openjdk:8-jre-alpine

# Set environment variables
ARG NEXUS_URL
ARG ARTIFACT_PATH
ARG VERSION=0.0.1-SNAPSHOT

# Install curl and wget, create the app directory, and download the jar file
RUN apk add --no-cache curl wget \
    && mkdir -p /app \
    && SNAPSHOT_VERSION=$(curl -s "${NEXUS_URL}/repository/maven-snapshots/${ARTIFACT_PATH}" | grep -o "<version>[^<]*" | sed 's/<version>//') \
    && wget ${NEXUS_URL}/repository/maven-snapshots/tn/esprit/spring/kaddem/${SNAPSHOT_VERSION}/kaddem-${SNAPSHOT_VERSION}.jar -O /app/kaddem.jar

# Set the working directory
WORKDIR /app
    # Set the entry point to run the jar
ENTRYPOINT ["java", "-jar", "/app/kaddem.jar"]
