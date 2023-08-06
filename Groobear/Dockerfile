0FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} bear.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java","-jar","/bear.jar"]

