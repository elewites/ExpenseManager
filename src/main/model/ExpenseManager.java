package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// Represents a container where the user can add their expenses, and check the total money spent
// for specific month or category
public class ExpenseManager {
    private List<Expense> expenses;         //list of expenses

    //EFFECTS: initializes an expense manager with an empty array list
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

    //EFFECTS: returns expense at index i
    public Expense getExpense(int i) {
        return expenses.get(i);
    }

//    //REQUIRES: m must have the format "year-month", ex. "2000-04",
//    //          in string "year-month", "-month" <= 12
//    //EFFECTS: returns total money spent in month m
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
