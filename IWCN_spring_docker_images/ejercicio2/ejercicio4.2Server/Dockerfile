FROM java:8
MAINTAINER Ruben Morante Gonzalez
EXPOSE 8080
VOLUME /tmp
ADD /target/TiendaProducto-0.0.1-SNAPSHOT.jar practicaruben.jar
ENTRYPOINT ["java", "-jar", "practicaruben.jar"]