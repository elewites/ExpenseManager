package model;


/* Represents an expense with an amount in dollars,
 * category (food, rent, medical, clothing, entertainment), and
 * month in which that dollar amount was spent
 */
public class Expense {
    private double amount;            //amount of money spent
    private String category;          //category of expense
    private String month;             //month in which amount was spent

    /*
     * REQUIRES: amount must be positive; category of expense
     *           must be from: food, rent, medical,
     *           clothing, entertainment;
     *           month string has the format "year-month", ex. "2000-04",
     *           in string "year-month", "-month" <= 12
     * EFFECTS:  dollar amount of expense is set to amount;
     *           category of expense is assigned to category,
     *           string category will always be lowercase
     *           month on which money was spent is assigned to month
     */
    public Expense(double amount, String category, String month) {
        this.amount = amount;
        this.category = category.toLowerCase();
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
