FROM openjdk:8u131-jdk-alpine

ENV PROJECT_PATH /opt/ativ-corba-server

COPY ./target/ativ-corba-server.jar ${PROJECT_PATH}/ativ-corba-server.jar	

CMD ["java", "-jar", "/opt/ativ-corba-server/ativ-corba-server.jar"]