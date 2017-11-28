FROM java:8u92-jdk-alpine
MAINTAINER Andriy Redko

COPY /jax-rs-2.0-cxf-metrics*.jar /

EXPOSE 19090

CMD  java -jar `ls /*.jar`