FROM openjdk:11-jre-slim
ARG VERSION=0.0.1-SNAPSHOT
VOLUME /tmp
ARG JAR_FILE=build/libs/onlinebanking.jar
COPY target/OnlineBanking-${VERSION}.jar app.jar
EXPOSE 8181
ENTRYPOINT ["java","-jar","app.jar"]
