FROM adoptopenjdk/openjdk11:jre-11.0.6_10-debianslim
EXPOSE 80
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
