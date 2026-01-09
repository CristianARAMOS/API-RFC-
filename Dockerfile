FROM eclipse-temurin:21-jdk


RUN groupadd --system appgroup \
    && useradd --system --gid appgroup --home /app --shell /sbin/nologin appuser

WORKDIR /app

COPY target/API-RFC-0.0.1-SNAPSHOT.jar app.jar


RUN chown -R appuser:appgroup /app

EXPOSE 8080

USER appuser

ENTRYPOINT ["java", "-jar", "app.jar"]
