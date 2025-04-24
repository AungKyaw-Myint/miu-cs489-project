FROM amazoncorretto:21-alpine
LABEL authors="DELL"

WORKDIR /app

COPY build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]