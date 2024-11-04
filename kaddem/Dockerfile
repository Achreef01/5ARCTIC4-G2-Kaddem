# Use a base Java image
FROM openjdk:8-jre-alpine

# Set environment variables for Nexus details
ARG NEXUS_URL
ARG ARTIFACT_PATH
ARG VERSION

RUN apk add --no-cache curl wget \
    && mkdir -p /app \
    && SNAPSHOT_VERSION=$(curl -s "${NEXUS_URL}/repository/Kaddem-AchrefOCHI-5Arctic4/${ARTIFACT_PATH}" | grep "<value>" | sed -E 's/.*<value>(.*)<\/value>/\1/') \
    && [ -n "$SNAPSHOT_VERSION" ] || { echo "Snapshot version not found."; exit 1; } \
    && wget ${NEXUS_URL}/repository/Kaddem-AchrefOCHI-5Arctic4/tn/esprit/spring/kaddem/${VERSION}/kaddem-${SNAPSHOT_VERSION}.jar -O /app/kaddem.jar

    # Set the entry point to run the jar
ENTRYPOINT ["java", "-jar", "/app/kaddem.jar"]
