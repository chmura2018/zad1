#wybor obrazu
FROM java
#aktualizacje
RUN apt-get update

#skopiowanie pliku w javie
COPY . /
WORKDIR /
#kompilacja pliku
RUN javac DockerConnectMySQL.java
CMD ["java", "-classpath", "mysql-connector-java-8.0.13.jar:.","DockerConnectMySQL"]

#Autor
LABEL maintainer="Tylec adrian.tylec@pollub.edu.pl"

