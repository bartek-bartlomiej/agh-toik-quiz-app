# Quiz App

Projekt z przedmiotu: Techniki komponentowe

Autorzy: Michał Śledź, Bartłomiej Stępień, Ludwik Ciechański

## Docker-compose local setup

1. Turn off existing MongoDB to have port 27017 free.
2. In backend catalog:
- package .jar file: `mvn clean package`
- make sure that .jar name is the same as in `docker-compose.yml`
- check `spring.data.mongodb.host` in `application.properties`
3. In frontend catalog:
- TODO
4. In project root:
- run all containers: `docker-compose up` (add `-d` to start them in detached mode)

## Remote server (AWS EC2)

1. Main API URL: http://ec2-3-126-103-190.eu-central-1.compute.amazonaws.com:8080/api/v1/

2. Swagger UI: http://ec2-3-126-103-190.eu-central-1.compute.amazonaws.com:8080/api/v1/swagger-ui.html

3. Web client: http://ec2-3-126-103-190.eu-central-1.compute.amazonaws.com:8081
