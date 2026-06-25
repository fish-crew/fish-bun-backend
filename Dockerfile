FROM gradle:8.10.2-jdk17-alpine AS builder

WORKDIR /app

COPY . .
RUN gradle clean bootJar -x test --no-daemon

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

ENV SPRING_PROFILES_ACTIVE=prod
ENV JAVA_OPTS=""

COPY --from=builder /app/build/libs/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
