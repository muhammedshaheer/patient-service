FROM openjdk:11.0-slim
WORKDIR /patient-service
ADD target/patient-service-0.0.1-SNAPSHOT.jar patient-service.jar

ENTRYPOINT ["java","-jar","patient-service.jar"]