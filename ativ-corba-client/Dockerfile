FROM openjdk:8u131-jdk-alpine

ENV PROJECT_PATH /opt/ativ-corba-client

COPY ./target/ativ-corba-client.jar ${PROJECT_PATH}/ativ-corba-client.jar

CMD ["java", "-jar", "/opt/ativ-corba-client/ativ-corba-client.jar"]