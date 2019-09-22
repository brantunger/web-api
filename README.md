# Web Api
Backend RESTful API for Website

# Setup Database Container
To setup a local database image make sure docker is installed and running. Then execute the following command to download and run MySQL:  

`docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=wildangels mysql:latest`

On future restarts of your machine get the docker CONTAINER_ID then restart the container:  
```
docker ps -a      
docker start CONTAINER_ID
```

To initialize the database run:
```
./gradlew flywayClean
./gradlew flywayMigrate
```