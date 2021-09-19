FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/number-converter-*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENV LOGDIR=/logs
ENTRYPOINT ["java","-jar","/app.jar"]