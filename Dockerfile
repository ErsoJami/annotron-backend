FROM openjdk:17-jdk-slim-buster AS builder

RUN apt-get update && apt-get install -y binutils

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY gradle ./gradle

RUN ./gradlew dependencies --no-daemon

COPY src ./src

RUN ./gradlew build --no-daemon -x test

FROM openjdk:17-jre-slim-buster

WORKDIR /app

COPY --from=builder /app/build/libs/tasks-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]