# Specify the base image
FROM openjdk:8-jre-alpine

# Define build arguments
ARG NEXUS_URL=http://192.168.56.4:8081
ARG VERSION=0.0.1

# Set environment variables for the application
ENV APP_HOME=/app
ENV JAR_FILE=kaddem-${VERSION}.jar

# Install necessary packages and download the jar
ARG ARTIFACT_PATH=tn/esprit/spring/kaddem/${VERSION}/kaddem-${VERSION}.jar
RUN apk add --no-cache curl wget \
    && mkdir -p ${APP_HOME} \
    && ARTIFACT_URL="${NEXUS_URL}/repository/maven-releases/${ARTIFACT_PATH}" \
    && echo "Attempting to download from: ${ARTIFACT_URL}" \
    && wget "${ARTIFACT_URL}" -O ${APP_HOME}/${JAR_FILE} \
    && ls -l ${APP_HOME} \
    || { echo "Failed to download JAR from ${ARTIFACT_URL}"; exit 1; }

# Set the working directory
WORKDIR ${APP_HOME}

# Command to run the jar file
CMD ["sh", "-c", "java -jar $APP_HOME/kaddem-0.0.1.jar"]
