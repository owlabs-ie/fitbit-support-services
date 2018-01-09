FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER flaviojmendes@gmail.com
VOLUME /app
ADD fitbit-tracker-1.0.0.jar fitbit-tracker-1.0.0.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/fitbit-tracker-1.0.0.jar"]
