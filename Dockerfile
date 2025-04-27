# Use the official OpenJDK 21 slim image
FROM eclipse-temurin:21-jdk-jammy

# Set working directory
WORKDIR /app

# Copy the built jar file
COPY target/account.jar account.jar

# Expose Spring Boot default port
EXPOSE 8002

# Run the application
ENTRYPOINT ["java", "-jar", "account.jar"]