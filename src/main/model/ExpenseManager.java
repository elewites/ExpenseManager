package model;

import model.enums.ExpenseCategory;
import model.enums.Month;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// Represents a container where the user can add their expenses, and check the total money spent
// for specific month or category
public class ExpenseManager {
    private List<Expense> expenses;               //list of expenses
    double totalCosts;                            //total money spent across ALL expenses

    //EFFECTS: initializes an expense manager with an empty array list
    public ExpenseManager() {
        expenses = new ArrayList<>();
        //formattedExpenses = new ArrayList<>();
    }

    //EFFECTS: returns list of expenses
    public List<Expense> getExpenses() {
        return expenses;
    }

    //EFFECTS: returns length of list of expenses
    public int getNumberOfExpenses() {
        return expenses.size();
    }

    //MODIFIES: this
    //EFFECTS: adds expense e to list of expenses
    public void addExpense(Expense e) {
        expenses.add(e);
    }

    //EFFECTS: returns expense at index i
    public Expense getExpense(int i) {
        return expenses.get(i);
    }

    //EFFECTS: returns true if list of expenses is empty, false otherwise
    public boolean expensesIsEmpty() {
        return expenses.isEmpty();
    }

    //EFFECTS: returns total money spent across ALL expenses
    public double total() {
        for (Expense e: expenses) {
            totalCosts += e.getAmount();
        }
        return totalCosts;
    }

    /* REQUIRES: m must have the format "year-month", ex. "2000-04",
    *            in string "year-month", "-month" <= 12
    *  EFFECTS: returns total money spent in month m
    */
//    public double getMonthlyTotal(String m) {
//        double total = 0;
//        for (Expense e: expenses) {
//            String expenseMonth = e.getMonth();
//            double expenseAmount = e.getAmount();
//            if (expenseMonth.equals(m)) {
//                total += expenseAmount;
//            }
//        }
//        return total;
//    }
//
//    /*
//     * REQUIRES: cat must be one of the following strings: food, rent, medical
//     *           clothing, or entertainment, not case-sensitive;
//     *           m must have the format "year-month",
//     *           in string "year-month", "-month" <= 12
//     * EFFECTS: returns total money spent for category cat and month m
//     */
//    public double getMonthlyTotalForCategory(String cat, String m) {
//        double total = 0;
//        for (Expense e: expenses) {
//            String expenseMonth = e.getMonth();
//            String expenseCategory = e.getCategory();
//            double expenseAmount = e.getAmount();
//            if (expenseMonth.equals(m) && expenseCategory.equals(cat.toLowerCase(Locale.ROOT))) {
//                total += expenseAmount;
//            }
//        }
//        return total;
//    }

}
