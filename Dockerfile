FROM gradle:8.5-jdk11 AS build

WORKDIR /app

COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY gradlew .
COPY gradle gradle

RUN chmod +x gradlew

COPY src src

RUN ./gradlew build -x test --no-daemon

FROM eclipse-temurin:11-jre-alpine

WORKDIR /app

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

# Параметры для оптимизации JVM в контейнере
ENV JAVA_OPTS="-Xmx512m -Xms256m -XX:+UseG1GC -XX:MaxRAMPercentage=75"

# Запуск приложения
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dserver.port=${PORT:-8080} -jar /app/app.jar"]