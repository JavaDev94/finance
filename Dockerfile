FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/finance-0.0.1.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]