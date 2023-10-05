# Mobile Testing Booking API

This API allows you to book or return mobile phones for testing purposes.

## Technologies

- Java
- Spring Boot
- Maven

## Build Instructions

To build this project, ensure you have a valid version of Maven and at least Java 8 installed. Then, run the following command:

```sh
mvn clean install verify
```

## Deployment

```sh
java -jar [Generated JAR File Name].jar
```

# API Details
Book a Phone for Testing
To book a mobile phone for testing, use the following curl command:

```sh
curl --location --request PUT 'http://<host>:8082/phone-testing-app/api/v1/book/Oneplus/9/user1'
```
Replace <host> with the actual host where the application is deployed.

# Return a Booked Phone
To return a booked mobile phone, use the following curl command:

```sh
curl --location --request PUT 'http://<host>:8082/phone-testing-app/api/v1/return/Oneplus/9/user1'
```










