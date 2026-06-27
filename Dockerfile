# ============================
# Build image — Maven + Java 25
# ============================
FROM eclipse-temurin:25-jdk-jammy AS build

WORKDIR /app

RUN apt-get update \
    && apt-get install -y maven \
    && rm -rf /var/lib/apt/lists/*

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

RUN ls -la target

RUN cp target/*.jar /app/app.jar

# ============================
# Runtime image — Java 25
# ============================
FROM eclipse-temurin:25-jre-jammy

RUN useradd -ms /bin/bash appuser

WORKDIR /app

COPY --from=build --chown=appuser:appuser /app/app.jar /app/app.jar

USER appuser

EXPOSE 9090

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/app.jar"]