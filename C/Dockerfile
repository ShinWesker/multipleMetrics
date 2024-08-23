FROM maven:3.8.1-openjdk-17-slim AS Builder

COPY . /app
WORKDIR /app

RUN mvn clean package -DskipTests

FROM openjdk:17-slim

ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar opentelemetry-javaagent.jar

ARG JAR_FILE=/app/target/*.jar
COPY --from=builder ${JAR_FILE} /app/app.jar

ENV OTEL_LOGS_EXPORTER none
ENV OTEL_METRICS_EXPORTER none
ENV OTEL_TRACES_EXPORTER otlp

ENV OTEL_EXPORTER_OTLP_TRACES_PROTOCOL grpc

ENTRYPOINT ["java", "-javaagent:/opentelemetry-javaagent.jar", "-jar", "/app/app.jar"]
