package model;

import java.util.Locale;

// Represents an expense having an amount in dollars, category, and
// the month in which that dollar amount was spent
public class Expense {
    private double amount;            //amount of money spent
    private String category;       //category of expense
    private String month;          //month in which amount was spent

    /*
     * REQUIRES: amount must be positive; category of expense
     *           must be food, rent, medical,
     *           clothing, or entertainment;
     *           month must have the format "year-month", ex. "2000-04",
     *           and month must be < 12
     * EFFECTS: dollar amount of expense is set to amount;
     *          category of expense is assigned to category
     *          month on which money was spent is assigned to month
     */
    public Expense(double amount, String category, String month) {
        this.amount = amount;
        String c = category.toLowerCase(Locale.ROOT);
        this.category = c;
        this.month = month;
    }

    //EFFECTS: returns amount of Expense
    public double getAmount() {
        return amount;
    }

    //EFFECTS: returns category of Expense
    public String getCategory() {
        return category;
    }

    //EFFECTS: returns date of Expense
    public String getMonth() {
        return month;
    }
}
