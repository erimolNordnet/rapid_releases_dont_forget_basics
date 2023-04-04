## Run Application 

### From IDE
1. Start Redis DB from command line: docker-compose up  
2. Run Application.java

### From Command Line
1. mvn clean package
2. docker-compose up
3. java -jar target/rapid-release-app-0.0.1-SNAPSHOT.jar

### Test when running:
- http://localhost:8080/companies/nordnet

## Query data in Redis when started in Docker
1. docker ps
2. copy CONTAINER_ID for running Redis instance
3. docker exec -it <CONTAINER_ID> /bin/sh
4. redis-cli
5. keys * (find all keys)
6. get nordnet (access data)
