# -------- Build Stage --------
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy only pom.xml to leverage Docker cache
COPY pom.xml .

# Pre-download dependencies
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# -------- Runtime Stage --------
FROM openjdk:21-jdk AS runner

WORKDIR /app

# Copy jar from builder stage
COPY --from=builder /app/target/analytics-service-0.0.1-SNAPSHOT.jar app.jar

# Expose app port
EXPOSE 4002

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
