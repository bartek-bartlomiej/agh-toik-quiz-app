# Quiz App

Projekt z przedmiotu: Techniki komponentowe

Autorzy: Michał Śledź, Bartłomiej Stępień, Ludwik Ciechański

## Docker-compose setup

1. Turn off existing MongoDB to have port 27017 free.
2. In backend catalog:
- package .jar file: `mvn clean package`
- make sure that .jar name is the same as in `docker-compose.yml`
- check `spring.data.mongodb.host` in `application.properties`
3. In frontend catalog:
- TODO
4. In project root:
- run all containers: `docker-compose up` (add `-d` to start them in detached mode)

## API

1. Main URL: http://localhost:8080/api/v1/
(by default redirects to: http://localhost:8080/api/v1/swagger-ui.html)
