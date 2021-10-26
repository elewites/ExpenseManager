package model;


import model.enums.ExpenseCategory;
import model.enums.Month;
import model.exceptions.DescriptionException;

/* Represents an expense with an amount in dollars,
 * category (food, rent, medical, clothing, entertainment), and
 * month in which that dollar amount was spent
 */
public class Expense {
    private String description;                //description of expense
    private double amount;                     //amount of money spent
    private ExpenseCategory category;          //category of expense
    private Month month;                       //month in which amount was spent
    private int year;                          //year in which amount was spent

    /* REQUIRES: chosenDescription.length() <= 30;
     *           expenseAmount > 0;
     *           chosenCategory is one of ExpenseCategory enums;
     *           chosenMonth is one of Month enums
     * EFFECTS:  chosenDescription is set to description;
     *           expenseAmount is set to amount;
     *           chosenCategory is assigned to category;
     *           chosenMonth is assigned to month
     *           year is assigned to this.year;
     */
    public Expense(String chosenDescription, double expenseAmount,
                   ExpenseCategory chosenCategory, Month chosenMonth, int year) {
        description = chosenDescription;
        amount = expenseAmount;
        category = chosenCategory;
        month = chosenMonth;
        this.year = year;
    }

    //EFFECTS: return description of Expense
    public String getDescription() {
        return description;
    }

    //EFFECTS: returns amount of Expense
    public double getAmount() {
        return amount;
    }

    //EFFECTS: returns category of Expense
    public ExpenseCategory getCategory() {
        return category;
    }

    //EFFECTS: returns month of Expense
    public Month getMonth() {
        return month;
    }

    //EFFECTS: returns year of Expense
    public int getYear() {
        return year;
    }






    //HELPERS BELOW

//    //EFFECTS: return true if myDescription.length() > 30 characters, false otherwise
//    public static boolean checkDescription(String myDescription) {
//        if (myDescription.length() > 30 || myDescription.isEmpty() || myDescription.equals(" ")) {
//            return true;
//        }
//        return false;
//    }

//    //EFFECTS: checks if expenseAmount is positive
//    //         if negative throws NegativeAmountException
//    private void checkExpense(double expenseAmount) throws NegativeAmountException {
//        if (expenseAmount < 0) {
//            throw new NegativeAmountException();
//        }
//    }
//
//    //MODIFIES: this
//    //EFFECTS: sets category to setToCategory
//    //         throws InvalidCategoryException if provided with invalid enum
//    private void setCategory(ExpenseCategory setToCategory) throws InvalidCategoryException {
//        ExpenseCategory[] categories = ExpenseCategory.values();
//        for (ExpenseCategory cat: categories) {
//            if (cat.equals(setToCategory)) {
//                category = setToCategory;
//            }
//        }
//        if (category == null) {
//            throw new InvalidCategoryException();
//        }
//    }
//
//    //MODIFIES: this
//    //EFFECTS: sets month to setToMonth
//    //         throws InvalidMonthException if provided with invalid enum
//    private void setMonth(Month setToMonth) throws InvalidMonthException {
//        Month[] months = Month.values();
//        for (Month m: months) {
//            if (m.equals(setToMonth)) {
//                month = setToMonth;
//            }
//        }
//        if (month == null) {
//            throw new InvalidMonthException();
//        }
//    }

}
