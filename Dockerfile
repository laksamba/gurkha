# # === STAGE 1: Build the app with Maven ===
# FROM eclipse-temurin:21-jdk AS builder

# # Set working directory
# WORKDIR /app

# # Copy Maven wrapper files and pom.xml first for layer caching
# COPY pom.xml .
# COPY .mvn .mvn
# COPY mvnw .

# # Copy the source code
# COPY src /app/src

# # Make the Maven wrapper executable
# RUN chmod +x mvnw

# # Build the Spring Boot app (skip tests for faster build)
# RUN ./mvnw clean package -DskipTests

# # === STAGE 2: Create the final image with just JRE ===
# FROM eclipse-temurin:21-jre

# WORKDIR /app

# # Copy the JAR from the builder stage
# COPY --from=builder /app/target/gurkha-website-0.0.1-SNAPSHOT.jar app-v1.jar

# # Expose port (matches Spring Boot default server.port)
# EXPOSE 9090

# # Start the application
# ENTRYPOINT ["java", "-jar", "app-v1.jar"]
