package ui;

import model.Expense;
import model.ExpenseManager;

import java.util.Locale;
import java.util.Scanner;

//Budget manager Application
//Code for interface based on CPSC 210 TellerApp
public class BudgetManagerApp {
    private ExpenseManager myExpenses;
    private Scanner input;

    //EFFECTS: runs the budget manager application
    public BudgetManagerApp() {
        runManager();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    public void runManager() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: processes user command c
    public void processCommand(String c) {
        if (c.equals("add")) {
            addExpense();
        } else if (c.equals("tm")) {
            monthExpense();
        } else if (c.equals("tmc")) {
            categoryMonthExpense();
        } else {
            System.out.println("\nMake a valid selection.");
        }
    }


    //MODIFIES: this
    //EFFECTS: initializes expense manager
    public void init() {
        myExpenses = new ExpenseManager();
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        System.out.println("Please type your name below:");
        String name = input.next();
        System.out.println("\nHi " + name + "! Welcome to your budget manager.");
    }

    //EFFECTS: displays menu  of options to user
    public void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tadd   -> Add an expense to your budget manager");
        System.out.println("\ttm  -> Look at the total money you have spent in a given month");
        System.out.println("\ttmc -> Look at the total money you spent per category of expense in a given month");
        System.out.println("\tq -> quit");
    }

    //MODIFIES: this
    //EFFECTS: adds an expense to expense manager
    public void addExpense() {
        double amount;
        String category;
        String date;

        System.out.println("\nChose one of the following categories of expense:");
        System.out.println("Food, Rent, Medical, Clothing, Entertainment");
        category = input.next();

        System.out.println("\nInput amount of expense (dollar):");
        amount = input.nextDouble();

        System.out.println("\nInput the date in which you spent the following amount: " + amount + " dollar/s");
        System.out.println("\nFormat for date should be year-month");
        System.out.println("Ex. 2021-08, 2021-11, etc.");
        System.out.println("Date: ");
        date = input.next();

        System.out.println("\nCategory: " + category);
        System.out.println("Amount: $" + amount);
        System.out.println("Date: " + date);

        Expense newExpense = new Expense(amount, category, date);
        myExpenses.addExpense(newExpense);
        System.out.println("Expense added!\n");
    }

    //EFFECTS: returns total amount of money spent for a given month
    public void monthExpense() {
        String month;
        System.out.println("For what month would you like to look at your balance:");
        System.out.println("Format for date should be year-month");
        System.out.println("Ex. 2021-08, 2021-11, etc.");
        System.out.println("Date: ");
        month = input.next();

        double monthlyTotal = myExpenses.getMonthlyTotal(month);

        System.out.println("\nExpenses for " + month + " month:");
        System.out.println("$" + monthlyTotal);
    }

    //EFFECTS: returns total amount of money spent monthly on a given category of expense
    public void categoryMonthExpense() {
        String month;
        String category;

        System.out.println("For what category would you like to look at your balance:");
        category = input.next();

        System.out.println("And for what month? Format for date should be year-month");
        System.out.println("ex. 2021-08, 2021-11, et.c");
        month = input.next();

        double monthlyExpense = myExpenses.getMonthlyTotalForCategory(category, month);

        System.out.println("\nExpenses for category " + category + " in month " + month);
        System.out.println("$" + monthlyExpense);
    }

}