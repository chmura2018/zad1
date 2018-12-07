FROM java:8
Label maintainers="Piotr Kalasa"
COPY . /
WORKDIR /
RUN javac plik.java
CMD 
