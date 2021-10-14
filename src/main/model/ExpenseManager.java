package model;

import java.util.ArrayList;
import java.util.List;

// Represents container where the user can add expenses, and check total money spent
// for specific a specific month or category
public class ExpenseManager {
    private List<Expense> expenses;         //list of expenses

    //EFFECTS: initializes expense manager with an empty array
    public ExpenseManager() {
        expenses = new ArrayList<>();
    }

    //EFFECTS: returns length of list of expenses
    public int numberOfExpenses() {
        return expenses.size();
    }

    //MODIFIES: this
    //EFFECTS: adds expense e to list of expenses
    public void addExpense(Expense e) {
        expenses.add(e);
    }

    //REQUIRES: m must have the format "year-month", ex. "2000-04"
    //EFFECTS: returns total money spent in month m
    public double getMonthlyTotal(String m) {
        double total = 0;
        for (Expense e: expenses) {
            String expenseMonth = e.getMonth();
            double expenseAmount = e.getAmount();
            if (expenseMonth.equals(m)) {
                total += expenseAmount;
            }
        }
        return total;
    }

    /*
     * REQUIRES: c must be string food, rent, medical
     *           clothing, or entertainment
     *           m must have the format "year-month"
     * EFFECTS: returns total money spent for category cat and month m
     */
    public double getMonthlyTotalForCategory(String cat, String m) {
        double total = 0;
        for (Expense e: expenses) {
            String expenseMonth = e.getMonth();
            String expenseCategory = e.getCategory();
            double expenseAmount = e.getAmount();
            if (expenseMonth.equals(m) && expenseCategory.equals(cat)) {
                total += expenseAmount;
            }
        }
        return total;
    }

}
