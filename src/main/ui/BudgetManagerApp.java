package ui;

import model.Expense;
import model.ExpenseManager;
import model.enums.ExpenseCategory;
import model.enums.Month;
import model.exceptions.DescriptionException;
import model.exceptions.InvalidCategoryException;
import model.exceptions.InvalidMonthException;
import model.exceptions.NegativeAmountException;

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
            System.out.println("ok i see u");
        } else if (c.equals("tm")) {
            //monthExpense();
        } else if (c.equals("tmc")) {
            //categoryMonthExpense();
        } else {
            System.out.println("\nMake a valid selection.");
        }
    }


    //MODIFIES: this
    //EFFECTS: initializes an empty expense manager
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
        System.out.println("\ttmc -> Look at the total money you spent for a category of expense in a given month");
        System.out.println("\tq -> quit");
    }

    //MODIFIES: this
    //EFFECTS: adds an expense to expense manager
    public void addExpense() {
        String description;
        double amount;
        ExpenseCategory enumCategory;
        String chosenMonth;
        Month enumMonth;
        int year;

        description = getDescription();
        enumCategory = getExpenseCategory();

        System.out.println("\nInput amount of expense (dollars):");
        amount = input.nextDouble();

        System.out.println("\nInput month of expense, e");

        //date = chooseMonthHelper();

       // System.out.println("\nCategory: " + category);
        System.out.println("Amount: $" + amount);
        //System.out.println("Date: " + date);

        Expense newExpense = null;

//        try {
//            newExpense = new Expense("cool", amount, chosenEnum, Month.APRIL, 2021);
//        } catch (DescriptionException e) {
//            e.printStackTrace();
//        } catch (NegativeAmountException e) {
//            e.printStackTrace();
//        } catch (InvalidCategoryException e) {
//            e.printStackTrace();
//        } catch (InvalidMonthException e) {
//            e.printStackTrace();
//        }
        myExpenses.addExpense(newExpense);
        System.out.println("Expense added!\n");
    }
//
//    //EFFECTS: returns total amount of money spent for a given month
//    public void monthExpense() {
//        String month;
//        System.out.println("For what month would you like to look at your balance:");
//        month = chooseMonthHelper();
//
//        double monthlyTotal = myExpenses.getMonthlyTotal(month);
//
//        System.out.println("\nExpenses for " + month + " month:");
//        System.out.println("$" + monthlyTotal);
//    }
//
//    //EFFECTS: returns total amount of money spent monthly on a given category of expense
//    public void categoryMonthExpense() {
//        String month;
//        String category;
//
//        System.out.println("For what category would you like to look at your balance:");
//        category = input.next();
//
//        System.out.println("And for what month?");
//        month = chooseMonthHelper();
//
//        double monthlyExpense = myExpenses.getMonthlyTotalForCategory(category, month);
//
//        System.out.println("\nExpenses for category " + category + " in month " + month);
//        System.out.println("$" + monthlyExpense);
//    }
//
//    //EFFECTS: returns the month which the user selected in format "year-month"
//    public String chooseMonthHelper() {
//        System.out.println("Format for date should be year-month, Ex. 2021-08, 2021-11, etc.");
//        System.out.println("Date: ");
//        return input.next();
//    }

    public boolean checkCategory(String chosenCategory) {
        try {
            ExpenseCategory.valueOf(chosenCategory.toUpperCase(Locale.ROOT));
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }

    }

    //EFFECTS: return true if myDescription.length() > 30 characters, false otherwise
    public boolean checkDescription(String myDescription) {
        if (myDescription.length() > 30 || myDescription.isEmpty() || myDescription.equals(" ")) {
            return true;
        }
        return false;
    }

    public String getDescription() {
        System.out.println("\nInsert description for expense:");
        String description = input.next();
        while (checkDescription(description)) {
            System.out.println("\nInvalid description, length must be between 1 and 30 characters:");
            description = input.next();
        }
        return description;
    }

    public ExpenseCategory getExpenseCategory() {
        String chosenCategory;
        ExpenseCategory enumCategory;

        System.out.println("\nChoose category for expense:");
        System.out.println("Food, Rent, Medical, Clothing, Entertainment");
        chosenCategory = input.next().toUpperCase(Locale.ROOT);
        while (!checkCategory(chosenCategory)) {
            System.out.println("\nInvalid category, try again:");
            chosenCategory = input.next().toUpperCase(Locale.ROOT);
        }
        enumCategory = ExpenseCategory.valueOf(chosenCategory.toUpperCase(Locale.ROOT));
        return enumCategory;
    }



}