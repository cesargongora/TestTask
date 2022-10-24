# Getting Started

## Install

Run install.cmd or
Run `mvnw install` to install all the packages, and any packages that it depends on.

## Configure DB

Configure DB in `\src\main\resources\application.properties`
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/bd_name
spring.datasource.username=user
spring.datasource.password=pass

## Run Server
Run run.cmd or
Run `mvnw spring-boot:run` for a server. Navigate to `http://localhost:8080/`.

## Postman Test
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/1bb72bbfab017f1fb3ce?action=collection%2Fimport)