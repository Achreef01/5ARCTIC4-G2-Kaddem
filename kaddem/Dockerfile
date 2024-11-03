# Use a base Java image
FROM openjdk:8-jre-alpine

# Set environment variables for Nexus details
ARG NEXUS_URL
ARG ARTIFACT_PATH
ARG VERSION

# Install necessary packages and download the .jar file from Nexus
RUN apk add --no-cache curl wget \
    && mkdir -p /app \
    && SNAPSHOT_VERSION=$(curl -s "${NEXUS_URL}/repository/Kaddem-AchrefOCHI-5Arctic4/${ARTIFACT_PATH}" | grep -oPm1 "(?<=<value>)[^<]+") \
    && wget ${NEXUS_URL}/repository/Kaddem-AchrefOCHI-5Arctic4/tn/esprit/spring/kaddem/${VERSION}/kaddem-${SNAPSHOT_VERSION}.jar -O /app/kaddem.jar

# Set the entry point to run the jar
ENTRYPOINT ["java", "-jar", "/app/kaddem.jar"]
