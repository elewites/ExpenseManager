package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a container where the user can add their expenses, and check the total money spent
// for specific month or category
public class ExpenseManager implements Writable {

    private List<Expense> expenses;               //list of expenses
    double totalCosts;                            //total money spent across ALL expenses
    private String user;                                  //user of this ExpenseManager

    //EFFECTS: initializes an expense manager with an empty array list
    public ExpenseManager() {
        user = "main user";
        expenses = new ArrayList<>();
    }

    //EFFECTS: returns user
    public String getUser() {
        return user;
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("user", user);
        json.put("expenses", expensesToJson());
        return json;
    }

    //EFFECTS: returns expenses in expense manager as a JSON array
    private JSONArray expensesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Expense e: expenses) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }


    //UNCOMMENTED CODE BELOW WILL BE USED IN LATER PHASES OF PROJECT

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
