FROM eclipse-temurin:17-jdk-alpine
VOLUME C:\dockerVol
ARG target\*.jar
COPY  target/*.jar customer.jar
ENTRYPOINT ["java","-jar","/customer.jar"]