# Stage 1: Build the application using Maven with JDK 21
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /build
COPY loja-app /build

WORKDIR /build 
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*
RUN mvn clean package

# Stage 2: Run the application using JDK 21
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /build/target/loja-app-1.0-jar-with-dependencies.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

