FROM openjdk:11
MAINTAINER joaovitor
COPY build/libs/cards-0.0.1-SNAPSHOT.jar cards-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","cards-0.0.1-SNAPSHOT.jar"]