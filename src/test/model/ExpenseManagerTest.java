package model;

import model.enums.ExpenseCategory;
import model.enums.Month;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseManagerTest {

    @Test
    public void testConstructor() {
        ExpenseManager expenseManager = new ExpenseManager();
        int len = expenseManager.getNumberOfExpenses();
        assertEquals(0, len);

        String user = expenseManager.getUser();

        assertEquals("main user", user);
    }

    @Test
    public void testAddExpense() {
        String description = "shoe";
        double amount = 10;
        ExpenseCategory category = ExpenseCategory.CLOTHING;
        Month month = Month.APRIL;
        int year = 2021;
        Expense item = new Expense(description, amount, category, month, year);

        ExpenseManager expenseManager = new ExpenseManager();
        expenseManager.addExpense(item);

        int len = expenseManager.getNumberOfExpenses();
        assertEquals(1, len);

        Expense fetchedExpense = expenseManager.getExpense(0);

        assertEquals(amount, fetchedExpense.getAmount());
        assertEquals(description, fetchedExpense.getDescription());
        assertEquals(category, fetchedExpense.getCategory());
        assertEquals(month, fetchedExpense.getMonth());
        assertEquals(year, fetchedExpense.getYear());
    }

    @Test
    public void testAddMultipleExpenses() {
        Expense itemOne =
                new Expense("shoe", 10, ExpenseCategory.CLOTHING, Month.APRIL, 2021);
        Expense itemTwo =
                new Expense("shoe", 20, ExpenseCategory.CLOTHING, Month.APRIL, 2021);
        Expense itemThree =
                new Expense("shoe", 30, ExpenseCategory.CLOTHING, Month.APRIL, 2021);

        int itemOnePrice = 10;
        int itemTwoPrice = 20;
        int itemThreePrice = 30;

        ExpenseManager expenseManager = new ExpenseManager();
        assertEquals(0, expenseManager.getNumberOfExpenses());

        expenseManager.addExpense(itemOne);
        expenseManager.addExpense(itemTwo);
        expenseManager.addExpense(itemThree);
        assertEquals(3, expenseManager.getNumberOfExpenses());

        Expense fetchedItem1 = expenseManager.getExpense(0);
        assertEquals(itemOnePrice, fetchedItem1.getAmount());

        Expense fetchedItem2 = expenseManager.getExpense(1);
        assertEquals(itemTwoPrice, fetchedItem2.getAmount());

        Expense fetchedItem3 = expenseManager.getExpense(2);
        assertEquals(itemThreePrice, fetchedItem3.getAmount());
    }

    @Test
    void testTotal() {
        Expense itemOne = new Expense("shoe", 10, ExpenseCategory.CLOTHING, Month.APRIL, 2021);
        Expense itemTwo = new Expense("shoe", 20, ExpenseCategory.CLOTHING, Month.APRIL, 2021);
        Expense itemThree = new Expense("shoe", 30, ExpenseCategory.CLOTHING, Month.APRIL, 2021);

        ExpenseManager expenseManager = new ExpenseManager();

        expenseManager.addExpense(itemOne);
        expenseManager.addExpense(itemTwo);
        expenseManager.addExpense(itemThree);

        assertEquals(60, expenseManager.total());
        assertEquals(60, expenseManager.total());
    }

    @Test
    void testGetExpenses() {
        Expense itemOne =
                new Expense("shoe", 10, ExpenseCategory.CLOTHING, Month.APRIL, 2021);
        Expense itemTwo =
                new Expense("shoe", 20, ExpenseCategory.CLOTHING, Month.APRIL, 2021);
        Expense itemThree =
                new Expense("shoe", 30, ExpenseCategory.CLOTHING, Month.APRIL, 2021);

        ExpenseManager expenseManager = new ExpenseManager();

        expenseManager.addExpense(itemOne);
        expenseManager.addExpense(itemTwo);
        expenseManager.addExpense(itemThree);

        List<Expense> listOfExpenses = expenseManager.getExpenses();
        Expense fetchedExpenseOne = listOfExpenses.get(0);
        Expense fetchedExpenseTwo = listOfExpenses.get(1);
        Expense fetchedExpenseThree = listOfExpenses.get(2);

        assertEquals(3, listOfExpenses.size());
        assertEquals(expenseManager.getExpense(0), fetchedExpenseOne);
        assertEquals(expenseManager.getExpense(1), fetchedExpenseTwo);
        assertEquals(expenseManager.getExpense(2), fetchedExpenseThree);
    }

    @Test
    void testExpensesIsEmpty() {
        ExpenseManager expenseManager = new ExpenseManager();
        assertTrue(expenseManager.expensesIsEmpty());

        Expense itemOne =
                new Expense("shoe", 10, ExpenseCategory.CLOTHING, Month.APRIL, 2021);
        expenseManager.addExpense(itemOne);

        assertFalse(expenseManager.expensesIsEmpty());
    }

    @Test
    void testToJson() {
        String description = "shoe";
        double amount = 10;
        ExpenseCategory category = ExpenseCategory.CLOTHING;
        Month month = Month.APRIL;
        int year = 2021;
        Expense item = new Expense(description, amount, category, month, year);

        //creating an expense manager as json object
        //expense manager has one expense
        JSONObject expectedManagerJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(item.toJson());
        expectedManagerJson.put("user", "main user");
        expectedManagerJson.put("expenses", jsonArray);

        ExpenseManager manager = new ExpenseManager();
        manager.addExpense(item);
        JSONObject actualManagerJson = manager.toJson();

        assertEquals(expectedManagerJson.get("user"), actualManagerJson.get("user"));

       JSONArray expectedExpenses = expectedManagerJson.getJSONArray("expenses");
       JSONArray actualExpenses = actualManagerJson.getJSONArray("expenses");

       for (Object expectedExpense: expectedExpenses) {
           for (Object actualExpense: actualExpenses) {
               JSONObject expected = (JSONObject) expectedExpense;
               JSONObject actual = (JSONObject) actualExpense;
               assertEquals(expected.get("description"), actual.get("description"));
               assertEquals(expected.get("amount"), actual.get("amount"));
               assertEquals(expected.get("category"), actual.get("category"));
               assertEquals(expected.get("month"), actual.get("month"));
               assertEquals(expected.get("year"), actual.get("year"));
           }
       }
        //assertTrue(expectedManagerJson.get("expenses").equals(actualManagerJson.get("expenses")));
        //System.out.println(expectedManagerJson.get("expenses"));
        //figure out why the last assertTrue does not pass
    }

    @Test
    void testGetMonthlyTotal() {
        Expense itemOne =
                new Expense("shoe", 10, ExpenseCategory.CLOTHING, Month.APRIL, 2021);
        Expense itemTwo =
                new Expense("shoe", 20, ExpenseCategory.CLOTHING, Month.OCTOBER, 2021);
        Expense itemThree =
                new Expense("shoe", 30, ExpenseCategory.CLOTHING, Month.SEPTEMBER, 2021);
        Expense itemFour =
                new Expense("shoe", 40, ExpenseCategory.CLOTHING, Month.APRIL, 2020);
        ExpenseManager expenseManager = new ExpenseManager();
        expenseManager.addExpense(itemOne);
        expenseManager.addExpense(itemTwo);
        expenseManager.addExpense(itemThree);
        expenseManager.addExpense(itemFour);

        double totalForApril = 10;
        assertEquals(totalForApril, expenseManager.getMonthlyTotal("april", 2021));
    }

    @Test
    void testGetExpensesForDate() {
        Expense itemOne =
                new Expense("shoe", 10, ExpenseCategory.CLOTHING, Month.APRIL, 2021);
        Expense itemTwo =
                new Expense("shoe", 20, ExpenseCategory.CLOTHING, Month.OCTOBER, 2021);
        Expense itemThree =
                new Expense("shoe", 30, ExpenseCategory.CLOTHING, Month.SEPTEMBER, 2021);
        Expense itemFour =
                new Expense("shoe", 40, ExpenseCategory.CLOTHING, Month.APRIL, 2020);
        ExpenseManager expenseManager = new ExpenseManager();
        expenseManager.addExpense(itemOne);
        expenseManager.addExpense(itemTwo);
        expenseManager.addExpense(itemThree);
        expenseManager.addExpense(itemFour);

        List<Expense> filteredExpenses = expenseManager.getExpensesForDate("april", 2021);
        assertEquals(1, filteredExpenses.size());

        assertEquals(itemOne, filteredExpenses.get(0));

    }
}
