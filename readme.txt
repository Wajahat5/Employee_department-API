-----------------EMPLOYEE-DEPARTMENT-API-------------------

Submitted by: Wajahat Yusuf
Emp Id: T2821
-----------------------------------------

This project allows users to  perform basic CRUD operations on a employee-department database. It also allows to fetch all employees from a particular department.
It was built using Spring JPA and uses MySql database to store the data.
By default, the database name is emp_dept_api, with user: root and password: root, running on port 3306. These can be modified as per requirement in the application.properties file.
Redis cache is used in this project, running on localhost at port 6379.
------------------------------------------

## Table structures ##

* Employee Table:

 int id -> Primary Key
 String name
 String location
 String manager_id
 String hire_date
 int salary
 Department department -> Foreign key that maps to department table

* Department Table:
 
 int id -> Primary Key
 String name
 String location

* The primary keys are auto-generated integers
------------------------------------------

## APIs ##

* url variable can hold value of localhost or deployed project url (By default project runs on localhost:8080)

* Department APIs

1) Add a new department:
  POST {{url}}/department
  request body:
  {
    "name":"dept2",
    "location":"loc2"
  }
2) Fetch all departments:
   GET {{url}}/department/all
3) Fetch particular department by its id:
   GET {{url}}/department/:id
4) Update department by id:
   PUT {{url}}/department/:id
   request body:
   {
    "name":"dept2 updated",
    "location":"loc2 updated"
   }
5) Delete department by its id:
   DELETE {{url}}/department/:id
6) Update multiple departments
   PUT {{url}}/department/multiple

* Employee APIs

1) Add a new employee:
   POST {{url}}/employee/:department_id
   request body:
   {
    "name":"emp2",
    "location" : "loc1",
    "manager_id" : "101",
    "hire_date" : "2022-01-01",
    "salary" :100000
   }
2) Fetch All employees:
   GET {{url}}/employee/all
3) Fetch particular employee by its id:
   GET {{url}}/employee/:id
4) Update a particular employee by its id:
   PUT {{url}}/employee/:id
   request body:
   {
    "name":"emp1 updated",
    "location" : "loc1 updated",
    "manager_id" : "101",
    "hire_date" : "2022-01-01",
    "salary" :100000,
    "departmentid" : 1
   }
5) Delete an employee by its id:
   DELETE {{url}}/employee/:id
6) Fetch all employees of a particular department:
   GET {{url}}/employee/department/:department_id

----------------------------------------
NOTE: The Emp_Dept_Spring_API.postman_collection file contains the json representation of the apis. Import it into postman to directly use
------------------------------ xxxx --------------------------------


 

