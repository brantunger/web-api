# Web Api
Backend RESTful API for Website

### Dependencies
Setup annotation processing and install lombok plugin in intelliJ.

# Setup Database Container
To setup a local database image make sure docker is installed and running. Then execute the following command to download and run MySQL:  

`docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=dreadfall mysql:latest`

To initialize the database run:
```
./gradlew flywayClean
./gradlew flywayMigrate
```
This will create several tables populated with data. You can now login to the frontend using the credentials with the role of **ADMIN**:
```
Username: admin
Password: password
```

On future restarts of your machine get the docker CONTAINER_ID then restart the container:  
```
docker ps -a      
docker start CONTAINER_ID
```

To login to the local database from a mysql client or GUI Database tool you can use the following credentials:
```
Username: root
Password: password
```