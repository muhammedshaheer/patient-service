# Patient Service
Developed using Java Spring Boot and Thymeleaf

## Stacks
1. Backend - Java Spring Boot Framework
2. Frontend - Thymeleaf
3. Database - MongoDB

### Run this application
1. Clone this repository
2. Run the below command to setup mongodb with docker
    ```
    docker-compose up
   ```
    
3. If you have mongodb in local system then that would be fine
4. If you are running with help of Intellij IDEA/Eclipse/STS, then import this project to your IDE
5. Run the main application PatientServiceApplication
6. The application will run on port 9090
7. Run this link in your browser
   
    ```
    http://localhost:9090
   ```

8. You can also run without help of an IDE 
   
    a. Open a command prompt in the root directory of this application
    
    b. Run the command 
   ```
   mvn clean install
   java -jar target/patient-service-0.0.1-SNAPSHOT.jar
    ```
   
