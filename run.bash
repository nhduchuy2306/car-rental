function init(){
    # copy application-environment.example.yml to application-environment.yml
    cp application-environment.example.yml ./src/main/resources/application-environment.yml
    # remove application-environment.yml from git
    git rm --cached src/main/resources/application-environment.yml
}

function startSpring(){
    mvn spring-boot:run -DskipTests -Pdevelopment
}

function deploySpring(){
    echo "pull all changes from git"
    git pull origin main

    echo "Build spring boot application base on staging yml"
    mvn clean package -DskipTests -Pstag

    echo "Deploy to staging server"
    scp -r target happygear@20.2.64.67:~/happygear

    echo "go to staging server and run docker-compose"
    ssh happygear@20.2.64.67 "cd happygear && docker-compose up -d --build api-server && docker exec -it happygear_nginx_1 nginx -s reload"
}

function buildSpring(){
    mvn clean package -DskipTests -Pdevelopment
}

function clearSpring(){
    mvn clean
}

function dockerComposeUp(){
    docker-compose up -d --build webapi mysql
}

if [ $1 == "start" ]; then
    startSpring
elif [ $1 == "deploy" ]; then
    deploySpring
elif [ $1 == "build" ]; then
    buildSpring
elif [ $1 == "clear" ]; then
    clearSpring
elif [ $1 == "docker" ]; then
    dockerComposeUp
elif [ $1 == "init" ]; then
    init
else
    echo "Please input correct command (start, deploy, build, clear)"
    exit 1
fi