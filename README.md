# Todo App  
A simple Spring Boot-based Todo management application using H2 Database, Thymeleaf, and Maven.

## Features
- Add, edit, delete tasks  
- Mark tasks as done or pending  
- View tasks filtered by status  
- In-memory H2 database with web console  

## Tech Stack
- Spring Boot  
- Spring Data JPA  
- H2 Database  
- Thymeleaf  
- Maven  

## Run Instructions
1. Clone this repo:  
   git clone https://github.com/yash-kaushik07/todoapp.git  
2. Navigate to the folder:  
   cd todoapp  
3. Run the app:  
   mvn spring-boot:run  
4. Open in browser:  
   http://localhost:8080/todos  
5. Access H2 Console:  
   http://localhost:8080/h2-console  
   (JDBC URL: jdbc:h2:mem:todo-db)

## Author
Yash Kaushik
