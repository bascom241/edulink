# First stage: build the application
FROM openjdk:17-jdk-slim AS builder  # Changed to 'builder' instead of non-existent 'build:latest'
WORKDIR /app
COPY . .
# Add your build commands here (e.g., mvn package, gradle build, etc.)

# Second stage: create the runtime image
FROM openjdk:17-jdk-slim  # Or use openjdk:17-jre-slim for smaller image
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/EdulinkServer-0.0.1-SNAPSHOT.jar .

# Expose port and run the application
EXPOSE 8080
CMD ["java", "-jar", "EdulinkServer-0.0.1-SNAPSHOT.jar"]