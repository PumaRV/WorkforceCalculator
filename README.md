# SPO Coding Challenge

A coding challenge consisting on the assignment of cleaning crews for different structures with varying room capacities. 
The goal is to minimize the overcapacity at every structure. 

# Dependencies
- Java 8 or superior
- Lombok
- Spring 
- The project is built using maven. 

# Running the application
1. Run ```$ mvn clean install```
2. Run ```$ mvn spring-boot:run``` 

This will start a web server and the API will be accesible through Http://localhost:8080

# Specs 
There is a single endpoint which will consume the contract information which consist of the number of rooms for every structure, 
the cleaning capacity of a Senior cleaner and the cleaning capacity of a Junior Cleaner. The endpoint will return the Optimal 
combination of senior and junior cleansers for each structure. 

GET /cleaner-crews

This endpoint is called to get the optimal cleaner crews. It requires a body: 

{   "rooms": [35, 21, 17, 28], 
    "senior": 10, 
    "junior": 6 
}

Where: 
- rooms: Is an array of integers representing the room capacity of each structure
- senior: represents the cleaning capacity of one senior cleaner. 
- junior: represents the cleaning capacity of one junior cleaner. 

ALL of the fields are required. 

Returns: 
200 - In case of success 
422 - If any of the fields fails validation
400 - If the Json body is invalid

Body: 
[
    {
        "senior": 3,
        "junior": 1
    },
    {
        "senior": 1,
        "junior": 2
    },
    {
        "senior": 2,
        "junior": 0
    },
    {
        "senior": 1,
        "junior": 3
    }
]

Where each pair of senior and junior values represent the optimal allocation for each structure. 

Additional Requirements: 
- The project needs to be developed in Java 8 
- The project needs to use Spring framework
- The maximum size for the structures is 100 (Configurable in application.properties)
- The maximum size for the rooms in any giving structure is 100 (Configurable in application.properties)
- Senior cleaning capacity must always be greater than junior cleaning capacity
- There needs to be, at least, one senior cleaner in every cleaning crew
