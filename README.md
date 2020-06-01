# Quiz App

Projekt z przedmiotu: Techniki komponentowe

Autorzy: Ludwik Ciechański, Bartłomiej Stępień, Michał Śledź

## Documentation
For generating system scheme, see [C4-PlantUML](https://github.com/RicardoNiepel/C4-PlantUML).

## Docker-compose local setup

1. Asure that port `270171` is free (turn off existing MongoDB if needed)
2. In `backend` catalog:
    - package .jar file: `mvn clean package`
    - make sure that .jar name is the same as in `docker-compose.yml`
    - check `spring.data.mongodb.host` value in `application.properties`
3. In `web-app` catalog:
    - build app: `npm install && npm run build`
    - make sure that created folder name is the same as in `docker-compose.yml`
4. In `/` project root:
    - run all containers: `docker-compose up` (add `-d` to start them in detached mode)

## Remote server (AWS EC2)

1. Main API URL: http://ec2-3-126-103-190.eu-central-1.compute.amazonaws.com:8080/api/v1/

2. Swagger UI: http://ec2-3-126-103-190.eu-central-1.compute.amazonaws.com:8080/api/v1/swagger-ui.html

3. Web client: http://ec2-3-126-103-190.eu-central-1.compute.amazonaws.com:8081
