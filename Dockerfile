FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/app
COPY  autoScoutExample-1.0-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/autoScoutExample-1.0-SNAPSHOT.jar"]