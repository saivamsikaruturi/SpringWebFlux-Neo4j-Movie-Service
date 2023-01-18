FROM openjdk:17
EXPOSE 8080
ADD target/movie-details-svc.jar movie-details-svc.jar
ENTRYPOINT ["java","-jar","movie-details-svc.jar"]
