FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/order-status-service-0.0.1-SNAPSHOT.jar order-status-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "kafka-app-0.0.1-SNAPSHOT.jar"]