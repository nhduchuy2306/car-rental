<h1 align="center">Spring boot project</h1>
<div id="badge" align="center">
  <!-- logo spring boot -->
  <img src="https://res.cloudinary.com/practicaldev/image/fetch/s--zrUJwvgZ--/c_imagga_scale,f_auto,fl_progressive,h_900,q_auto,w_1600/https://dev-to-uploads.s3.amazonaws.com/uploads/articles/bupbqc9fctvw4j7r14it.png">
  <!-- image of spring boot -->
  <img src="https://img.shields.io/badge/Spring%20Boot-3.0.6-brightgreen">
  <!-- image of java -->
  <img src="https://img.shields.io/badge/Java-17-red">
  <!-- image of maven -->
  <img src="https://img.shields.io/badge/Maven-3.9.1-blue">
  <!-- image of docker -->
  <img src="https://img.shields.io/badge/Docker-23.0.5-blue">
  <!-- image of docker-compose -->
  <img src="https://img.shields.io/badge/Docker%20Compose-2.17.3-blue">
  <!-- image of docker-desktop -->
  <img src="https://img.shields.io/badge/Docker%20Desktop-4.19.0-blue">
</div>
<h1 align="center">Car Rental</h1>
<div align="center">
  <img src="https://media0.giphy.com/media/26BRrcK4dXrxl817q/giphy.gif">
</div>

1. Create file environment.yml using this command:

```
cp application-environment.example.yml ./src/main/resources/application-environment.yml

git rm --cached src/main/resources/application-environment.yml
```

***Note:*** If you have bash command, you can run bash script using this command: `sh run.bash init`

2. Do some task before starting project:

- Make sure you have bash commandline installed on your machine if you want to run bash script.
  - Fill the `application-environment.yml` in `src/main/resources` with your own configuration.
  - Must have maven version 3.5.0 or later.
  - Must have jdk verison 17 or later.
  - Make sure you have docker and docker-compose installed on your machine.

3. Start docker container using this command: `docker-compose up -d`
4. Run this project using this command make sure you using bash commandline:

- Using bash command line, choose one of the following commands:
  - `sh run.bash start`
  - `mvn clean install -Pdevelopment && java -jar target/carrental-1.0.jar`
  - `mvn clean install -Pdevelopment && mvn spring-boot:run -Pdevelopment`
  - `mvn clean install -Pdevelopment && ./mvnw spring-boot:run -Pdevelopment`
  - Finally, you can run this project using your IDE
