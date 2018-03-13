FROM openjdk:8-jdk-alpine
MAINTAINER flaviojmendes@gmail.com
VOLUME /tmp
ADD target/fitbit-tracker.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
#ENV LANG C.UTF-8
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar " ]