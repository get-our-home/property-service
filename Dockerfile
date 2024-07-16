FROM openjdk:17-jdk

WORKDIR /app

COPY build/libs/*.jar property-service.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "property-service.jar"]