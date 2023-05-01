<h1 align="center">Spring boot project</h1>
<div id="badge" align="center">
  <!-- logo spring boot -->
  <img src="https://res.cloudinary.com/practicaldev/image/fetch/s--zrUJwvgZ--/c_imagga_scale,f_auto,fl_progressive,h_900,q_auto,w_1600/https://dev-to-uploads.s3.amazonaws.com/uploads/articles/bupbqc9fctvw4j7r14it.png">
  <!-- image of spring boot -->
  <img src="https://img.shields.io/badge/Spring%20Boot-2.4.5-brightgreen">
  <!-- image of java -->
  <img src="https://img.shields.io/badge/Java-11-red">
  <!-- image of maven -->
  <img src="https://img.shields.io/badge/Maven-3.8.1-blue">
  <!-- image of docker -->
  <img src="https://img.shields.io/badge/Docker-20.10.6-blue">
  <!-- image of docker-compose -->
  <img src="https://img.shields.io/badge/Docker%20Compose-1.29.1-blue">
</div>
<h1 align="center">Car Rental</h1>
<div align="center">
  <img src="https://media0.giphy.com/media/26BRrcK4dXrxl817q/giphy.gif">
</div>

1. Create file environment.yml using this command:

```
cp environment.example.yml ./src/main/resources/environment.yml
```

```
git rm --cached src/main/resources/environment.yml
```

---

2. Fill the environment.yml with your own configuration

---

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
