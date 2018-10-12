FROM openjdk:10-jre
ADD ./target/Movie-Cruiser-App-0.0.1-SNAPSHOT.jar /usr/app/Movie-Cruiser-App-0.0.1-SNAPSHOT.jar
WORKDIR usr/app
ENTRYPOINT ["java","-jar", "Movie-Cruiser-App-0.0.1-SNAPSHOT.jar"]
