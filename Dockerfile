# Use an official Maven Image to build the spring booot app
FROM maven:3.9.6-eclipse-temurin-17 AS build


# set the working directory
WORKDIR /app

# copy the pom.xml and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline


# ---------- BUILD STAGE ----------
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom and source together
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests


# ---------- RUNTIME STAGE ----------
FROM amazoncorretto:17-alpine AS runtime

WORKDIR /app

# Copy the built jar (no hardcoding version)
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]


## now we need to build and create an image using this docker file
## build the image using ## docker build -t edulink-deployment .
## tag the application ## docker tag edulink-deployment:latest bascotee/edulink-deployment:latest
## push to deployment docker push bascotee/edulink-deployment:latest
