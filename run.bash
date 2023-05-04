function initSpring(){
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
    mvn clean package -DskipTests -Pstaging

    echo "Deploy to staging server"
    scp -r target happygear@20.2.64.67:~/carrental

    echo "go to staging server and run docker-compose"
    ssh happygear@20.2.64.67 "cd carrental && docker-compose up -d --build webap && docker exec -it happygear_nginx_1 nginx -s reload"
}

function buildSpringInDevelopment(){
    mvn clean package -DskipTests -Pdevelopment
}

function buildSpringInStaging(){
    mvn clean package -DskipTests -Pstaging
}

function clearSpring(){
    mvn clean
}

function dockerComposeUp(){
    docker-compose up -d --build webapi mysql
}

function deployToAzure(){
    mvn azure-spring-apps:deploy
}

if [ $1 == "init" ]; then
    initSpring
elif [ $1 == "start" ]; then
    startSpring
elif [ $1 == "build-dev" ]; then
    buildSpringInDevelopment
elif [ $1 == "build-stag" ]; then
    buildSpringInStaging
elif [ $1 == "docker" ]; then
    dockerComposeUp
elif [ $1 == "deploy" ]; then
    deploySpring
elif [ $1 == "clear" ]; then
    clearSpring
else
    echo "Please input command (init, start, deploy, build-dev, build-stag, clear)"
    exit 1
fi