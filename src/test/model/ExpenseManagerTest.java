package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseManagerTest {

    @Test
    public void testConstructor() {
        ExpenseManager myExpenses = new ExpenseManager();
        int len = myExpenses.numberOfExpenses();
        assertEquals(0, len);
    }

    @Test
    public void testAddExpense() {
        double a = 10;
        String c = "clothing";
        String m = "2021-10";
        Expense shoe = new Expense(a, c, m);

        ExpenseManager myExpenses = new ExpenseManager();
        myExpenses.addExpense(shoe);

        int len = myExpenses.numberOfExpenses();

        assertEquals(1, len);
        //do I need to make sure the expense I added is the correct one?
        //if so how?
        //im thinking I need to add a unique identifier to expenses?
    }

    @Test
    public void testGetMonthlyTotalWithMultipleExpensesInList() {
        Expense shoe = new Expense(10, "clothing", "2021-10");
        Expense groceries = new Expense(100, "food", "2021-10");
        Expense concertTicket = new Expense(500, "entertainment", "2021-10");

        Expense newShoe = new Expense(100, "clothing", "2021-09");
        Expense augustRent = new Expense(1000, "rent", "2021-08");
        Expense augustJuly = new Expense (1000, "rent", "2021-07");

        ExpenseManager myExpenses = new ExpenseManager();
        myExpenses.addExpense(shoe);
        myExpenses.addExpense(groceries);
        myExpenses.addExpense(concertTicket);
        myExpenses.addExpense(newShoe);
        myExpenses.addExpense(augustRent);
        myExpenses.addExpense(augustJuly);

        //how can I make this test more clean?

        double octoberTotal = myExpenses.getMonthlyTotal("2021-10");

        assertEquals(610, octoberTotal);
    }

    @Test
    public void testGetMonthlyTotalForCategory() {
        Expense shoe = new Expense(15, "clothing", "2021-10");
        Expense shirts = new Expense(500, "clothing", "2021-10");
        Expense groceries = new Expense(100, "food", "2021-10");
        Expense concertTicket = new Expense(500, "entertainment", "2021-10");

        Expense newShoe = new Expense(100, "clothing", "2021-09");
        Expense augustRent = new Expense(1000, "rent", "2021-08");
        Expense augustJuly = new Expense (1000, "rent", "2021-07");

        ExpenseManager myExpenses = new ExpenseManager();
        myExpenses.addExpense(shoe);
        myExpenses.addExpense(shirts);
        myExpenses.addExpense(groceries);
        myExpenses.addExpense(concertTicket);
        myExpenses.addExpense(newShoe);
        myExpenses.addExpense(augustRent);
        myExpenses.addExpense(augustJuly);

        double octoberClothingTotal = myExpenses.getMonthlyTotalForCategory("clothing", "2021-10");

        assertEquals(515, octoberClothingTotal);

    }

}
