# My Personal Project

## Budget Manager

The **intent** of the Budget Manager application is to provide a 
user-friendly system that helps manage one's expenses. Within the application
the user is able to catalog their expenses by date and category.  

In addition, the user is able to view the total money they 
have spent for any given month. On top of that, they can also view the total money 
spent for any given category in any given month.

The application is completely functional with two distinct user interface.
Inside the ui package, you can find a console based user interface and a 
graphical user interface. The default interface is the gui. To use the console
interface, you must go to Main.java and make the appropriate changes.

The app is made to be **used** by a range of users.
Anyone who is interested in having access to an easy way 
of tracking their monthly budget/expenses should be able to use this platform.

*Ex*. students, professors, teenagers, parents, etc.


The reason this project is of **interest** to me is 
because as a university student learning to manage your expenses is crucial. 
With the creation of this app I intend ease the
burdens of such process.

Documented below you can find the progression of development. 
The project was completed in 4 phases. 

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

*This user story below (#4) has not been implemented yet, will work on it after phase 2 submission*
4. As a user, I want to be able to view all expenses for a 
given month. Each expense should output in format:


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
