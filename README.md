
1. Create file environment.yml using this command:
```
cp environment.example.yml ./src/main/resources/environment.yml
```
***
2. Fill the environment.yml with your own configuration
***
3. Start docker container using this command:
```
docker-compose up -d
```
4. Run this project using this command make sure you using bash commandline:
- if you have bash commandline, run this command:
`bash run.bash`
- if you don't have bash commandline, run this command:
`mvn clean install && java -jar target/carrental-1.0.jar`
- Or you can run this project using this command:
`mvn clean install && mvn spring-boot:run`
- Or this command:
`mvn clean install && ./mvnw spring-boot:run`
- Finally, you can run this project using your IDE