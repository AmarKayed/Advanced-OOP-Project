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
    This class represents a base class/superclass for the classes Customer and Employee subclasses. This class models the general properties of any person, whether it is a customer(client of the bank) or an employee(a person who works for the bank, usually under a contract).
    
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
    This class represents a base class/superclass for the classes CurrentAccount and SavingsAccount subclasses. This class models the general properties of any bank account, whether it is a CurrentAccount(used for day-to-day transactions/purposes) or a SavingsAccount(used for saving money).

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
    This class derives from the Account superclass. It models the properties of a normal "savings account" in a bank. A savings account is an account with the sole purpose of saving/storing money deposits. Furthermore, it can be used to gain interest on the deposited amount, meaning that the bank will pay the account holder for the deposited money via a specified interest rate. The interest rate represents a percentage of the total amount deposited and is paid in a "Compound Interval". The compound interval is the interval in which the account holder will be paid the interest rate for his/her deposits. The compound interval is usually on a yearly basis, but it can be sooner or later than that.

    Attributes:
    * interest == of type float, it represents the interest rate for the deposited balance
    * compoundInterval == of type int, it represents the number of months for which the payment of the interest

7. ## Address
    This is an auxiliary class, used for storing information about a geographical address. For the sake of simplicity, we considered a valid address being any combination of a country and a city belonging to that country.

    Attributes:
    * country == of type String, it represents the name of a country
    * city == of type g, it represents the name of a city belonging to the above mentioned country

8. ## Transaction
    This class models the main properties of a typical bank transaction. Every transaction must have an IBAN of the account which performs the transaction, as well as a date and an amount. For the sake of simplicity, the bank will perform only three types of transactions:
    * Deposit: when money is added to the current balance of the account identified by the IBAN
    * Withdraw: when money is subtracted/taken out of the current balance belonging to the account identified by the IBAN
    * Transfer: which is essentially withdrawing from one account and depositing into another account.

        > Three **conditions** must be mentioned:
        >
        > 1. The bank only performs interchange transactions, meaning that transfers will only occur between account of the same bank, not between different banks
        > 2. We can identify if a transaction is a deposit or a withdraw by the Transaction.amount attribute:
        
        > ```
        > if Transaction.amount < 0:
        >     // it is a withdraw, the account is losing money.
        > else if Transaction.amount > 0:
        >     // it is a deposit, the account is gaing money.
        > ``` 
        > 3. Since a transfer will be considered as two separate transactions, meaning a transfer will be a deposit and a withdraw respectively, we will not be able to identify/differentiate if a deposit/withdraw was a result of a transfer or not.

    Attributes:
    * IBAN == of type int, representing the account number which performs the transaction
    * transactionDate == of type String, representing the date in which the transaction was performed. The format of the date is the same as previously mentioned

        