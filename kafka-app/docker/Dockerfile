FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/kafka-app-0.0.1-SNAPSHOT.jar kafka-app-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "kafka-app-0.0.1-SNAPSHOT.jar"]