FROM eclipse-temurin:17

LABEL mentainer="mohdziyadk@gmail.com"

WORKDIR /app

COPY target/e-commerce-restapi-0.0.1-SNAPSHOT.jar /app/e-commerce-restapi.jar

ENTRYPOINT ["java", "-jar", "e-commerce-restapi.jar"]