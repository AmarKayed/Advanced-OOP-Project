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

Besides the main entities, the project will provide a general Service class, with the purpose of handling the various services of the application, as well as a main class, responsible for calling the before mentioned services.

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
    This class represents an extension of the Person class, thus denoting that every Employee **IS-A** Person. The Employee class represents the employees of the bank.

    Attributes:
    * salary == of type float, it represents the monthly net salary of the employee, measured in USD
    * hireDate == of type String, it represents the date in which the employee got hired/oficially and contractually started working for the bank. The format of the date will be *'dayNumber MONTH yearNumber'*.
    
        For example: **'25 MAR 2022'**
    
4. ## Account
    This class represents a base class/superclass for the classes CurrentAccount and SavingsAccount subclasses. This class models the general attributes of any bank account, whether it is a CurrentAccount(used for day-to-day transactions/purposes) or a SavingsAccount(used for saving money).

    Attributes:
    * IBAN == of type int, the IBAN stands for "International Bank Account Number", meaning that it is basically a unique identification number/code given to an account
    * openDate == of type String, it represents the date at which the account was opened. This attribute has the same format as Employee.hireDate
    * holder == object of type Customer, via this object we get all the information about the holder/owner of this bank account
    * balance == of type float, it represents the current available money/deposit amount associated to the bank account

5. ## CurrentAccount
    This class derives from the Account superclass. It models the properties of a normal "current account" in a bank. A current account is an account use for normal day-to-day activities. It is meant for both depositing and withdrawing money, as well as spending the available balance.

    Attributes:
    * commission == of type float, represents the commission rate for the administration of the account. Usually the commission rate is between 1-10%
    * transactionHistory == a List collection of objects of type Transaction, it represents the list of all the transactions made for the account. The Transaction class will be explained at index #8

6. ## SavingsAccount
    This class derives from the Account superclass.
7. ## Address
8. ## Transaction