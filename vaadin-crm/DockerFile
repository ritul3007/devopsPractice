FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} vaadin-crm-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/vaadin-crm-1.0-SNAPSHOT.jar"]
EXPOSE 8085