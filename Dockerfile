FROM java
RUN apt-get update
LABEL maintainers="Piotr Kalasa"
COPY . /
WORKDIR /
RUN javac DockerConnectMySQL.java
CMD ["java", "-classpath", "mysql-connector-java-8.0.13.jar:.","DockerConnectMySQL"]
