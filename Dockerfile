FROM openjdk:8-jdk-alpine
RUN addgroup -S currencyconverter && adduser -S currencyconverter -G currencyconverter
USER currencyconverter:currencyconverter
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]