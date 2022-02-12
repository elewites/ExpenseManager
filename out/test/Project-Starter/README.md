# Budget Manager

## Purpose
The **intent** of the Budget Manager application is to provide a 
user-friendly system that helps manage one's expenses. 

Within the application the user is able to catalog their expenses by date and category.  

Another personal goal of this project was to familiarize myself with the 
principles of object-oriented programming. 

## App Conventions
The application is completely functional with two distinct user interfaces.

Inside the ui package, you can find a console based user interface and a 
graphical user interface. 

The default interface is the gui. To use the console
interface, you must go to Main.java and make the appropriate changes.


Documented below you can find the progression of development and 
the user stories which I came up with to guide me through the design process. 

These user stories express everything that the user should be able to do
through my application: 

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
given month and see the total expenses for that given month only. 
Each expense should output in format:


    description-category-amount-month-year


In addition, I want to be able to view the total money spent 
for that given MONTH. 


    Total Costs: $$$$


*This user story below (#5) has not been implemented yet, will work on it after phase 2 submission*
5. As a user, I want to be able to view total expenses
per category and per month

### Phase 2 User Stories
1. As a user, when I select the quit option from the application menu, 
I want to be reminded to save the changes made to my budget manager. 
2. As a user, when I start my budget manager, I want to be given
the option to load my expenses from file. 


### Phase 4: Task 2
*representative sample of the events that occur when your program runs*

Thu Nov 25 22:48:18 PST 2021
New Expense added to expense manager

Expenses saved!

Thu Nov 25 22:48:24 PST 2021
New Expense added to expense manager

Loaded expenses from ./data/expensemanager.json

Event log:
1. Thu Nov 25 22:48:18 PST 2021
   New Expense added to expense manager
2. Thu Nov 25 22:48:24 PST 2021
   New Expense added to expense manager


### Phase 4: Task 3
Reflection on Design
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
