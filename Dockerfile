FROM openjdk:21

WORKDIR /app

COPY target/jobportal-0.0.1-SNAPSHOT.jar /app/jobportal.jar

EXPOSE 8080

CMD ["java", "-jar", "jobportal.jar"]
