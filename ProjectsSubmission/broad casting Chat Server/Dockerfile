# TODO Add Docker dependencies or  an UBUNTU base image and start the relevant 
FROM openjdk:16-jdk-alpine
VOLUME /chat-app-backend
ADD chat/target/chat-0.0.1.war app.war
EXPOSE 9090
ENTRYPOINT ["java", "-jar","/app.war"]