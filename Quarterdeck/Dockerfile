FROM openjdk:10-jdk-slim

ENV ENV docker

WORKDIR /opt/Quarterdeck

COPY quarterdeck.example.yaml /opt/Quarterdeck/quarterdeck.yaml
COPY Quarterdeck.jar /opt/Quarterdeck/Quarterdeck.jar

EXPOSE 4269

ENTRYPOINT ["java", "-Xmx128m", "-jar", "Quarterdeck.jar"]
