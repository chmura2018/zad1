FROM java
RUN apt-get update
Label maintainers="Piotr Kalasa"
COPY . /
WORKDIR /
RUN javac plik.java
CMD ["java", "-classpath", "mysql-connector-java-8.0.11.jar:.","plik"]
