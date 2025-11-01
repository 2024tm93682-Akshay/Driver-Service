# ===============================
# Step 1: Use a base image with Java 17
# ===============================
FROM openjdk:17-jdk-slim

# ===============================
# Step 2: Set working directory inside the container
# ===============================
WORKDIR /app

# ===============================
# Step 3: Copy the built JAR file from your local build
# Make sure to run 'mvn clean package -DskipTests' before building this image
# ===============================
COPY target/DriverServiceApplication-0.0.1-SNAPSHOT.jar app.jar

# ===============================
# Step 4: Copy configuration files into the container
# Ensure that src/main/resources/application-docker.properties exists
# ===============================
COPY src/main/resources/application-docker.properties /app/config/application-docker.properties

# (Optional) If you plan to use data.sql or schema.sql for seeding data, copy them too
COPY src/main/resources/*.sql /app/config/

# ===============================
# Step 5: Set environment variables
# This tells Spring Boot to load the docker profile
# ===============================
ENV SPRING_PROFILES_ACTIVE=docker

# ===============================
# Step 6: Expose the port your Spring Boot app runs on
# ===============================
EXPOSE 8082

# ===============================
# Step 7: Command to run the Spring Boot application
# ===============================
ENTRYPOINT ["java", "-jar", "app.jar"]
