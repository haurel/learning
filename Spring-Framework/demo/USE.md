###Use Postman app.
POST Method - localhost:8080/api/v1/person
    - json: 
            {
                "name" : "First Last"
            }

GET Method - localhost:8080/api/v1/person
    - return all name added with POST Method.

#Using In-memory DB.

#Demo project : https://spring.io/quickstart
#Generate project: start.spring.io


Launch spring:
./mvnw spring-boot:run