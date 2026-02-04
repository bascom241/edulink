# --------------------------
# Stage 1: Build the app
# --------------------------
FROM maven:3.9.2-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the jar
RUN mvn clean package -DskipTests

# --------------------------
# Stage 2: Run the app
# --------------------------
FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/EdulinkServer-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java","-jar","app.jar"]
