# This Is An Advanced Object Oriented Programming Project Written In Java!

## **Description**
This project represents a Console Application written in Java, using advanced OOP concepts.

The project aims to simulate the management of a **Banking System**.

The main entities of this project are:
* Person
* Customer
* Employee
* Account
* CurrentAccount
* SavingsAccount
* Address
* Transaction

## **Classes**
1. ## Person
    This class represents a base class/superclass for the classes Customer and Employee subclasses. This class models the general attributes of any person, whether it is a customer(client of the bank) or an employee(a person who works for the bank, usually under a contract).
    
    Attributes:
    * id == of type int, it is the equivalent of a Social Security Number or a National Identification Number => it is a unique identity number assigned to each distinct person
    * lastName == of type String, it represents the last name/surname/family name of a person
    * firstName == of type String, it represents the first name of a person
    * gender == of type char, it represents the gender of a person, which in the context of the project will be considered as binary. Therefore, there will be only two possible values:
        * 'M' for male
        * 'F' for female



        
2. ## Customer
    This class represents an extension of the Person class, thus denoting that every Customer **IS-A** Person. The Customer class represents the customers of our bank. For the sake of simplicity, the bank will only require customers to provide their ID number, address and job title. The bank will not impose any restrictions based on the background or income of a potential customer, thus any person can be a customer provided that they posses a stable residency address and are currently employed.
    
    Attributes:
    * address == object of type Address. This is an encapsulated object given as an attribute and thus ilustrates the principle of class composition. The Address class will be further explained at index #7
    * job == of type String, it represents the job title of the customer


3. ## Employee
4. ## Account
5. ## CurrentAccount
6. ## SavingsAccount
7. ## Address
8. ## Transaction