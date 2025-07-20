# Use a Java 24 base image
FROM eclipse-temurin:24-jdk as build

# Set work directory
WORKDIR /app

# Copy the Spring Boot jar built by Gradle
COPY build/libs/*.jar app.jar

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
