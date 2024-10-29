# Use a base Java image
FROM openjdk:8
 
# Set up ARG variables for Nexus details
ARG NEXUS_URL
ARG ARTIFACT_PATH
ARG VERSION
 
# Download the .jar file from Nexus during the build
RUN apt-get update && apt-get install -y wget \
&& mkdir -p /app \
&& wget ${NEXUS_URL}/${ARTIFACT_PATH}/${VERSION}/kaddem-${VERSION}.jar -O /app/kaddem.jar
 
# Set the entry point to run the jar
ENTRYPOINT ["java", "-jar", "/app/kaddem.jar"]