# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built WAR file into the container
COPY target/on-boarding-project-0.0.1-SNAPSHOT.war on-boarding-project.war

# Expose the port your application runs on
EXPOSE 8080

# Run the Spring Boot WAR file
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/on-boarding-project.war"]
