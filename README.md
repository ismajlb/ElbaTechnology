# ELBA Technology Excel Service

## Welcome to the ELBA Side Excel Service! 
This service is responsible for reading and saving data from Excel files as part of the ELBA Technology application.

<h2>Features</h2>
Reads and saves data from Excel files and writing into the database using Apache POI.

Exposes service as a RESTful web service. Integrates with a MySQL database using Spring Boot Data JPA.
Ensures data integrity with transactions.

<h2>Requirements</h2>
Java 8 or higher

MySQL 8 or higher

<h2>Dependencies</h2>
Apache POI
Lombok
Spring Boot Data Rest
Spring Boot Data JPA
MySQL Connector/J
Set up the application
Clone the repository from GitHub: 
    
    git clone https://github.com/elbatechnology

Navigate to the root directory of the project: 

    cd elbatechnology
Install the dependencies: 

    mvn install

Run the application: 

    mvn spring-boot:run

The application will be available at 
    
    http://localhost:8080/excel/import

## Set up the database
Run the MySQL server on your machine.

Create a new database for the application: CREATE DATABASE employeedb;

Run the database creation script from the repository: 

    mysql -u <username> -p employeedb < database.sql

The script will create the necessary tables and insert some sample data into the database.

<h2>How to use</h2>
The service provides a method readAndSaveExcelFile which accepts a MultipartFile object representing an Excel file. The method reads the data from the file using Apache POI and saves it to the database using the provided repositories.

The data in the Excel file is organized in two types: employee data and department data. The employee data is used to create Employee and Member entities, while the department data is used to create Department and LeaderDepartment entities.

The method returns an object of type UploadExcelFileResponse, which contains information about the success or failure of the operation.

Author
[Ismajl Bina]
