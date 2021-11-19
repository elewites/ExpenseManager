package model;

import model.enums.Month;
import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// Represents a container where the user can add their expenses, and check the total money spent
// for specific month or category
public class ExpenseManager implements Writable {

    private final List<Expense> expenses;               //list of expenses
    private final String user;                          //user of this ExpenseManager


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
        double totalCosts = 0;
        for (Expense e: expenses) {
            totalCosts += e.getAmount();
        }
        return totalCosts;
    }

    @Override
    //EFFECTS: returns this as a JSON object
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


    //EFFECTS: returns total money spent in given date
    public double getMonthlyTotal(String month, int year) {
        String monthUpper = month.toUpperCase(Locale.ROOT);
        double total = 0;
        for (Expense e: expenses) {
            Month fetchedMonth = e.getMonth();
            String fetchedMonthUpper = fetchedMonth.toString().toUpperCase(Locale.ROOT);
            int fetchedYear = e.getYear();

            double expenseAmount = e.getAmount();
            if (fetchedMonthUpper.equals(monthUpper) && fetchedYear == year) {
                total += expenseAmount;
            }
        }
        return total;
    }

    //EFFECTS: returns expenses that were purchased only in given date
    public List<Expense> getExpensesForDate(String month, int year) {
        List<Expense> filteredExpenses = new ArrayList<>();
        String monthUpper = month.toUpperCase(Locale.ROOT);

        for (Expense e: expenses) {
            Month fetchedMonth = e.getMonth();
            String fetchedMonthUpper = fetchedMonth.toString().toUpperCase(Locale.ROOT);
            int fetchedYear = e.getYear();

            if (fetchedMonthUpper.equals(monthUpper) && fetchedYear == year) {
                filteredExpenses.add(e);
            }
        }
        return filteredExpenses;
    }

}
