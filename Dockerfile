# Use a lightweight base image with Java runtime
FROM eclipse-temurin:17-jre-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot jar file into the container
COPY target/pastries-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on (optional but good practice)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
