# Budget Manager

## Purpose
The **intent** of the Budget Manager application is to provide a 
user-friendly system that helps manage one's expenses. 

Within the application the user is able to catalog their expenses by date and category.  

Additional personal goals of this project
1. To build code that adheres to the principles of object-oriented programming.
2. Use data abstraction to model a real world problem. 
3. To develop clean, readable, and structured code. 
4. To learn how to implement tests to make sure my data abstractions are performing
as required. 

## App Conventions
The application is completely functional with two distinct user interfaces.

Inside the ui package, you can find a console based user interface and a 
graphical user interface. 

The default interface is the gui. To use the console
interface, you must go to Main.java and make the appropriate changes.


## User Stories

Documented below you can find the progression of development and 
the user stories which guided me through the design process. 

The following user stories express everything that the user should be able to do
through my application.

### Phase 1 User Stories
1. As a user, I want to be able to add an expense 
to my list of expenses. 
2. As a user, I want to be able to add a description, dollar amount,
category, month, and year to each expense as I am adding 
them to my expense list.
3. As a user, I want to be able to view all expenses in my 
expense list. Each expense should output in format:


    description-category-amount-month-year


In addition, I want to be able to view the total money spent for ALL expenses.


    Total Costs: $$$$


4. As a user, I want to be able to view filter my expenses for a 
given month and see those expenses in a list. 

Each expense should output in format:


    description-category-amount-month-year


### Phase 2 User Stories
1. As a user, when I select the quit option from the application menu, 
I want to be reminded to save the changes made to my budget manager. 
2. As a user, when I start my budget manager, I want to be given
the option to load my expenses from file through a load file button. 


### Phase 3 & 4
The EventLog class was a component of this project which was 
implemented in Phase 4. 

The EventLog class, logs an Event to the console everytime something
in the application occurs. 

- E.g. if you add an expense to the expense manager the following is 
logged to the console:

Thu Nov 25 22:48:18 PST 2021

New Expense added to expense manager


- E.g. if you save the expenses in your expense manager, the following
is logged: 

Expenses saved!

- E.g. if you load expenses from file:

Loaded expenses from ./data/expensemanager.json

- E.g. when you close the expense manager, a list
of every expense added is logged to the console like this:

Event log
1. Thu Nov 25 22:48:18 PST 2021
   New Expense added to expense manager
2. Thu Nov 25 22:48:24 PST 2021
   New Expense added to expense manager


### Analysis and UML Design Diagram
**Reflection on Design**

In the root folder, I have included a UML Class Diagram of my
completed project. 

The diagram shows the overall architecture of the project, the 
different classes and the relationships between them 
(associations, inheritance, dependencies, compositions, etc.).

After completing the diagram, I came up with the following analysis
regarding aspects I would refactor in the future for this project. 


* I would refactor the Popup component. I feel it is strange to have multiple
frames within the application. It would be more efficient to only
have the MainAppFrame and have the Popup live somewhere inside that
MainAppFrame as a panel. This way only the MainAppFrame would have access
to ExpenseManager, eliminating the coupling between Popup and ExpenseManager. 


* Again, the elimination of Popup would also eliminate the coupling between 
Popup and ListPanel. This would decrease overall coupling in the system. 


* I would also like to eliminate the dependency between JsonReader and 
EventPrinter. That dependency exists because I could not another way 
of logging an Event to EventLog when expenses are loaded from file. However,
I would like for that logic to take place in EventPrinter. This way, the 
MainAppFrame will be the only class that has access to EventPrinter, reducing
overall coupling.
