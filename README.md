
# Local backend launch:

 ## 1. Build

```bash
mvn clean package -DskipTests
```

## 2. Run the app
  When first time run the app, use the next command to build containers and launch them:
```bash
docker compose --env-file=docker_local.env up --build -d 
```

 Then, this command can be used to run:
```bash
docker compose --env-file=docker_local.env up -d
```

## 3. Access the Services
  Previous command launches all the microservices, mongoDb, postgreDb, and kafka (both zookeeper and kafka itself)

* Kafka-ui: <localhost:8090>
* MongoDb manager (mongo express): <localhost:8081>
* Postgres admin: <localhost:8888>
* Eureka page: <localhost:8761>


  For logs, you can use the docker desktop app or the below command
```bash
  docker logs -t -f <service name>
```
(service names are inside docker-compose file)

## 4. Stop
You can stop all your services just by killing current terminal's process (Ctrl + C)

## 5. Remove containers and volumes if not used
  ```bash
docker compose down -v  
```


All the properties are inside 'docker_local.env' file. If you want to run services with different properties (like for local run or prod environment run without using docker), configure your own ***.env file and use it in the run configuration


