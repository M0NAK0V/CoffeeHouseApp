FROM adoptopenjdk/openjdk11:alpine-jre
ADD build/libs/Coffeehouse-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]