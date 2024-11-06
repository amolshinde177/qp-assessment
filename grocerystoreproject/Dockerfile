FROM openjdk

WORKDIR /app

COPY target/grocery-store-project-docker.jar /app/grocery-store-project-docker.jar

EXPOSE 9090

ENTRYPOINT [ "java", "-jar", "grocery-store-project-docker.jar"]