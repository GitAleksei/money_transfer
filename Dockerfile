FROM adoptopenjdk/openjdk11
EXPOSE 8080
ADD target/money_transfer-0.0.1-SNAPSHOT.jar transfer.jar
ENTRYPOINT ["java", "-jar", "transfer.jar"]