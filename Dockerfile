FROM openjdk:12-jdk-oraclelinux7

ENV AB_OFF=true

EXPOSE 8080/tcp

ENTRYPOINT ["java", "-Djava.net.preferIPv4Stack=true", "-jar", "app.jar"]

COPY target/spring-boot-hello-world-1.0-SNAPSHOT.jar app.jar