# GIPHY SERVICE

`Prerequisites: Install Docker`

### Clean Build
 For a clean build, install Java 11 and Maven version 3.6.3  
`mvn clean install`

### Build Docker image

`docker build -t giphy-spring-boot .`


### Run Docker image

`docker run -p 8081:8081 giphy-spring-boot`

### Test Endpoint

API endpoint to test it with

http://localhost:8081/search/cheese  
http://localhost:8081/search/{search_term}

API Key is stored in application.properties. Can modify that for other keys and do a clean build
