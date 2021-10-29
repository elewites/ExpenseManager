package ui;

import model.Expense;
import model.ExpenseManager;
import model.enums.ExpenseCategory;
import model.enums.Month;
import persistance.JsonWriter;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

//Budget manager Application
//Code for user interface based on CPSC 210 TellerApp
public class BudgetManagerApp {
    private static final String JSON_STORE = "data/workroom.json";
    private ExpenseManager budgetManager;
    private Scanner input;
    private JsonWriter writer;

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
    //EFFECTS: initializes an empty expense manager
    public void init() {
        budgetManager = new ExpenseManager();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        writer = new JsonWriter(JSON_STORE);
    }

    //MODIFIES: this
    //EFFECTS: processes user command c
    public void processCommand(String c) {
        switch (c) {
            case "add":
                addExpense();
                break;
            case "vis":
                visualizeExpenses();
            case "tm":
                //monthExpense();
                break;
            case "tmc":
                //categoryMonthExpense();
                break;
            case "s":
                saveExpenses();
                break;
            default:
                System.out.println("\nMake a valid selection.");
                break;
        }
    }

    //EFFECTS: displays menu  of options to user
    public void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tadd -> Add an expense to your budget manager");
        System.out.println("\tvis -> Visualize ALL expenses added to your budget manager and cumulative money spent");
        System.out.println("\tm   -> Look at expenses and total money spent for a given month");
        System.out.println("\ttmc -> Look at the total money you spent for a category of expense in a given month");
        System.out.println("\ts   -> save expenses to file");
        System.out.println("\tq   -> quit");
    }

    //MODIFIES: this
    //EFFECTS: adds an expense to expense manager
    public void addExpense() {
        String description;
        double amount;
        ExpenseCategory category;
        Month month;
        int year;

        description = parseDescriptionInput();
        category = parseCategoryInput();
        amount = parseAmountInput();
        month = parseMonthInput();
        year = parseYear();

        Expense newExpense = new Expense(description, amount, category, month, year);
        budgetManager.addExpense(newExpense);
        System.out.println("\n" + newExpense);
        System.out.println("Expense added!");
    }

    //EFFECTS: prints all expenses in the user's list of expenses,
    //         In addition, prints cumulative money spent
    public void visualizeExpenses() {
        List<Expense> expenses = budgetManager.getExpenses();

        if (budgetManager.expensesIsEmpty()) {
            System.out.println("Your expense list is empty");
        } else {
            for (Expense e : expenses) {
                System.out.println(e);
            }
        }
        System.out.println("Total Costs: " + budgetManager.total());
    }

    //EFFECTS: saves expenses in budget manager to file
    public void saveExpenses() {
        try {
            writer.open();
            writer.write(budgetManager);
            writer.close();
            System.out.println("Expenses saved!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save expenses to file: " + JSON_STORE);
        }
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

    //HELPER METHODS BELOW

    //EFFECTS: returns description chosen by user
    public String parseDescriptionInput() {
        System.out.println("\nInsert description for expense:");
        String description = input.next();

        while (!checkDescription(description)) {
            System.out.println("\nInvalid description, length must be between 1 and 30 characters:");
            description = input.next();
        }
        return description;
    }

    //EFFECTS: return true if myDescription.length() IS NOT > 30 characters, if it IS NOT empty,
    //         or if string DOES NOT contain space only, false otherwise
    public boolean checkDescription(String myDescription) {
        return !(myDescription.length() > 30 || myDescription.isEmpty() || myDescription.equals(" "));
    }

    //EFFECTS: returns category chosen by user
    public ExpenseCategory parseCategoryInput() {
        String chosenCategory;
        ExpenseCategory enumCategory;

        System.out.println("\nChoose category for expense:");
        System.out.println("Food, Rent, Medical, Clothing, Entertainment");
        chosenCategory = input.next().toUpperCase(Locale.ROOT);

        while (!checkCategory(chosenCategory)) {
            System.out.println("\nInvalid category, try again:");
            chosenCategory = input.next().toUpperCase(Locale.ROOT);
        }

        enumCategory = ExpenseCategory.valueOf(chosenCategory);
        return enumCategory;
    }

    //EFFECTS: returns true if chosenCategory is associated with a valid ExpenseCategory enum,
    //         false otherwise
    public boolean checkCategory(String chosenCategory) {
        try {
            ExpenseCategory.valueOf(chosenCategory.toUpperCase(Locale.ROOT));
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }

    }

    //EFFECTS: returns month chosen by user
    public Month parseMonthInput() {
        String chosenMonth;
        Month enumMonth;

        System.out.println("\nInput month of expense, e.g October.");
        chosenMonth = input.next().toUpperCase(Locale.ROOT);

        while (!checkMonth(chosenMonth)) {
            System.out.println("\nInvalid input, try again:");
            chosenMonth = input.next().toUpperCase(Locale.ROOT);
        }
        enumMonth = Month.valueOf(chosenMonth);
        return enumMonth;
    }

    //EFFECTS: returns true if chosenMonth is associated with a valid Month enum,
    //         false otherwise
    public boolean checkMonth(String chosenMonth) {
        try {
            Month.valueOf(chosenMonth.toUpperCase(Locale.ROOT));
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }

    }

    //EFFECTS: returns amount chosen by user
    public double parseAmountInput() {
        double amount;

        System.out.println("\nInput amount of expense (dollars):");

        amount = input.nextDouble();

        while (!checkAmount(amount)) {
            System.out.println("\nInvalid input, dollar amount must be > 0");
            amount = input.nextDouble();
        }
        return amount;
    }

    //EFFECTS: returns true if amount > 0, false otherwise
    public boolean checkAmount(double amount) {
       //Double.parseDouble()
        return amount > 0;
    }

    //EFFECTS: returns year chosen by user
    public int parseYear() {
        int year;
        System.out.println("\nInput year of expense:");
        year = input.nextInt();
        return year;
    }

}