# spring-initiation
A repository I use to try spring

Following this course : [Spring Boot Tutorial for Beginners - 2023 Crash Course using Spring Boot 3](https://www.youtube.com/watch?v=UgX5lgv4uVM&ab_channel=DanVega)

I adapt the code organisation, the variable name that are not very clear in the tutorial
I also change a bit the method in the class to add more control and well separate the methods and class by responsibility

## Prerequisite:
* Java > 17
* A postgresql database

## Adapt properties
In [application.properties](./src/main/resources/application.properties)

Adapt the value of the connection to the db :
```text
spring.datasource.url= # THE URL OF YOUR DATABASE
spring.datasource.username=# THE USER IN YOUR DATABASE
spring.datasource.password=# THE PASSWORD IN YOUR DATABASE
```

In production mode we should define this value dynamically during the build (during the CIp hase). 
At least the password shouldn't be in clear in the code

## Launch the application

Use the maven wrapper to launch the application :
```bash
./mvnw spring-boot:run
```

