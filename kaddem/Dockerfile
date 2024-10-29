# Use a base Java image
FROM openjdk:8
 
# Set up ARG variables for Nexus details
ARG NEXUS_URL
ARG ARTIFACT_PATH
ARG VERSION
 
# Download the .jar file from Nexus during the build

RUN apt-get update && apt-get install -y wget curl \
    && mkdir -p /app \
    && SNAPSHOT_VERSION=$(curl -s "${NEXUS_URL}/repository/Kaddem-MohamedMrabet-5Arctic4/${ARTIFACT_PATH}/${VERSION}/maven-metadata.xml" | grep -oPm1 "(?<=<value>)[^<]+") \
    && wget ${NEXUS_URL}/repository/Kaddem-MohamedMrabet-5Arctic4/${ARTIFACT_PATH}/${VERSION}/kaddem-${SNAPSHOT_VERSION}.jar -O /app/kaddem.jar


 
# Set the entry point to run the jar
ENTRYPOINT ["java", "-jar", "/app/kaddem.jar"]