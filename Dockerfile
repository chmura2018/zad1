FROM java:8
Label maintainers="Piotr Kalasa"
WORKDIR /
RUN javac plik.java
CMD 
