# ---------- Build Stage ----------
# JDK image
FROM eclipse-temurin:21-jdk AS build

# Set working directory inside the container
WORKDIR /app

# Copy Maven files and build the project
COPY . .

# Build the application
RUN ./mvnw clean package -DskipTests

# ---------- Run Stage ----------
# Minimal JRE image to run the app
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the built jar from build stage
COPY --from=build /app/target/rental-manager-api-*.jar app.jar

# Expose the backend port
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
