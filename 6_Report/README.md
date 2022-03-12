EMPLOYEE MANAGEMENT SYSTEM-(EMS)


Introduction:-

    What is EMS ?

Employee Management System is a distributed application, developed to maintain the details of employees working in any organization The EMS has been developed to override the problems prevailing in the practicing manual system.
It maintains the information about the personal and official details of the employees.

Objectives Of Project:-

This project aims to simplify the task of maintaining records of the employees of Company.

To develop an well-designed database to store employee information.

Provides full functional reports to management of Company.

The objective of this project is to provide a comprehensive approach towards the management of employee information.

Cost and Time Complexity:-

Time Complexity:

O(Clog⁡N), where N is the number of employees, and C is the number of jobs across all employees. The maximum size of the heap is N, so each push and pop operation is O(log⁡N), and there are O(C) such operations.

Space Complexity:
O(N) in additional space complexity.

Defining System:




![WhatsApp Image 2022-03-12 at 11 39 10 AM](https://user-images.githubusercontent.com/74421461/158013573-b61a5b89-1883-4d91-8ce0-22780a43d5c8.jpeg)
                           





                                


![WhatsApp Image 2022-03-12 at 11 32 32 AM](https://user-images.githubusercontent.com/74421461/158013688-087a756d-d637-4005-93eb-a511177a32f5.jpeg)










Explanation:-


Employee Management has some features.

Proper Login Screen (Invisible Password Typing).

Password Protected.

Encrypted File (Binary).

Easily Add, Delete, Modify Records.

Various Essential Queries.


SWOT Analysis:



             




![WhatsApp Image 2022-03-12 at 12 41 07 PM](https://user-images.githubusercontent.com/74421461/158013837-2da483c9-e99f-4607-90dd-2355269c5180.jpeg)

               



Some of the common benefits of having an EMS in your organization are :-

 Efficiency And Better Accuracy
 
 Fewer Compliance Risks

 Boosted Profitability

 Very Few Manual Errors

 Higher Productivity

 Higher Motivation

 Lower Costs
                        

Implementing an EMS framework for workers is equivalent to pushing mountains for a company and that is really important when it comes to handling a remote workforce.


The Employee Management System is now used in every industry to have all the info of the employee stored into file/database. This way all the list of employees working in a particular industry can be stored in one place


This issue can be solved by creating a database of employees and saving the information in a file.


High Level Requirements:

| ID | Description | Category |	Status |
| ---- | ------------------- | ----- | ---- |
| HR01 | cilent shall be able to add Employee record | Techincal	| TBD-S1 |
| HR02 | cilent shall be able to display Employee record | Techincal | TBD-S1 |
| HR03 | cilent shall be able to update a Employee record	| Techincal |	TBD-S1 |
| HR04 | cilent shall be able to delete a Employee record	| Techincal |	TBD-S1 |
| HR05 | cilent shall be able to save records in a file |	Techincal	| TBD-S1 |
| HR06 | cilent shall be able to read data from a file | Techincal | TBD-S1 |
| HR07 | Data should not be lost in case of faliure |	Scenario | FUTURE |
| HR08 | Data should be stored when closing the system | Scenario | TBD-S1 |



Low level Requirements:



| ID | Description |	HLR ID |	Status (Implemented/Future) |
| ---- | -------------- | ---- | ----- |
| LR01 |	New record is added and Employee id should be unique | HR01 | TBD-S1 |
| LR02 |	Finding the Employee details can be either by searching by name or the best way of searching is by Employee id | HR02 |	TBD-S1 |
| LR03 |	If user searches for an invalid ID ""ERROR RECORD NOT FOUND" message should be displayed | HR02 |	TBD-S1 |
| LR04 |	User need to search by the Employee id for the details to be updated, if no record is available then "ERROR RECORD NOT FOUND" Message should be displayed |	HR02 |	TBD-S1 |
| LR06 |	User need to search by id for the Employee record to be deleted, if no such record is available then "ERROR RECORD NOT FOUND" Message should be displayed |	HR04 |	TBD-S1 |
| LR05 |	User shall be able to save the files, if file already exists then it should append to file and should not overwrite it and if file does not exists then it should create a new file |	HR06 | TBD-S1 |
| LR06 |	If opening the login page fails system shloud prompt the message "Invalid login" |	HR07 | TBD-S1 |
| LR07 |	After adding each record it asks whether you need to add an another record or Not and Display message as "Y/N" if want to add/modify/delete another record can click Y/N	 | HR01,HR02,HR03,HR04 | TBD-S1 |




Best Methods To Be Followed

Used OOPS concepts to decrease dependency on code

Used Arraylist and arrays to accept the inputs from user and store the values which helped in creating easy design of Employee management system.

Println statements have been placed only wherever necessary to avoid confusions

Created Packages file so that the OOPS concepts can be used else where ever required without any difficulty

Unit testing is done to avoid any computational errors.


Behavioural Diagram:
