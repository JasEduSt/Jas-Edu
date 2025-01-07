FROM openjdk:21-jdk-slim
WORKDIR /opt
COPY target/*.jar /opt/app.jar
ENV PORT 8080
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
