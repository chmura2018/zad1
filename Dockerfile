FROM java
RUN apt-get update
Label maintainers="Piotr Kalasa"
COPY . /
WORKDIR /
RUN javac DockerConnectMySQL.java
CMD ["java", "-classpath", "mysql-connector-java-8.0.11.jar:.","DockerConnectMySQL"]
