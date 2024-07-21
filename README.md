# Outbox pattern with event listner using java

## To run PostgreSQL service using docker

- make file executable

  ```
  chmod +x ./start-postgres.sh
  ```
- execute script

  ```
  ./start-postgres.sh
  ```
- check container is running, container name: outbox_event_listner_pg

  ```
  docker ps
  ```

*Or if PostgreSQL is already running then modify the credintals in the ../resources/application.yaml file*

## To run server

```
mvn clean spring-boot:run
```

## Hit APIs

#### To import json file to postman collection

1. Open Postman
2. Click on the import button
3. Select the JSON file and import it

#### Alternatively, you can simply run this cURL command for user registration, as there is only one API

```
curl --location 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "fullName": "full name",
    "email": "user@gmail.com",
    "password": "pass123",
    "phone": "9800000000"
}'
```
