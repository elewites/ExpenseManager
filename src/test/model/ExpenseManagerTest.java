package model;

import model.enums.ExpenseCategory;
import model.enums.Month;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.management.monitor.Monitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpenseManagerTest {
    private ExpenseManager myExpenses;
    private Expense shoes;
    private Expense newShoes;
    private Expense groceries;
    private Expense dinner;
    private Expense concertTicket;
    private Expense party;
    private Expense augustRent;
    private Expense julyRent;
    private Expense brokenArm;
    private Expense brokenRibs;


    @BeforeEach
    void runBefore() {
//        //clothing expenses
//        shoes = new Expense(10, "clothing", "2021-10");
//        newShoes = new Expense(40, "clothing", "2021-10");
//
//        //food expenses, spent on 2021-10
//        groceries = new Expense(100, "food", "2021-10");
//        dinner = new Expense(30, "food", "2021-10");
//
//        //entertainment expenses, include months: 2021-01 and 2021-02
//        concertTicket = new Expense(500, "entertainment", "2021-01");
//        party = new Expense(100, "entertainment", "2021-02");
//
//        //rent expenses, include months: 2021-07 and 2021-08
//        julyRent = new Expense (1000, "rent", "2021-07");
//        augustRent =  new Expense(1000, "rent", "2021-08");
//
//        //medical expenses, include months: 2021-06 and 2021-07
//        brokenRibs = new Expense(7000, "medical", "2021-06");
//        brokenArm = new Expense (6000, "medical", "2021-07");
//
//        //new expense manager
//        myExpenses = new ExpenseManager();
//
//        //adding expenses to expense manager
//        myExpenses.addExpense(shoes);
//        myExpenses.addExpense(newShoes);
//        myExpenses.addExpense(groceries);
//        myExpenses.addExpense(dinner);
//        myExpenses.addExpense(concertTicket);
//        myExpenses.addExpense(party);
//        myExpenses.addExpense(julyRent);
//        myExpenses.addExpense(augustRent);
//        myExpenses.addExpense(brokenArm);
//        myExpenses.addExpense(brokenRibs);

    }

    @Test
    public void testConstructor() {
        ExpenseManager myExpenses = new ExpenseManager();
        int len = myExpenses.numberOfExpenses();
        assertEquals(0, len);
    }

    @Test
    public void testAddExpense() {
        String description = "shoe";
        double amount = 10;
        ExpenseCategory category = ExpenseCategory.CLOTHING;
        Month month = Month.APRIL;
        int year = 2021;
        Expense item = new Expense(description, amount, category, month, year);

        ExpenseManager coolExpenses = new ExpenseManager();
        coolExpenses.addExpense(item);

        int len = coolExpenses.numberOfExpenses();
        assertEquals(1, len);

        Expense fetchedExpense = coolExpenses.getExpense(0);

        assertEquals(amount, fetchedExpense.getAmount());
        assertTrue(description.equals(fetchedExpense.getDescription()));
        assertTrue(category.equals(fetchedExpense.getCategory()));
        assertTrue(month.equals(fetchedExpense.getMonth()));
        assertEquals(year, fetchedExpense.getYear());
    }

    @Test
    public void testAddMultipleExpenses() {
        Expense itemOne = new Expense("shoe", 10, ExpenseCategory.CLOTHING, Month.APRIL, 2021);
        Expense itemTwo = new Expense("shoe", 20, ExpenseCategory.CLOTHING, Month.APRIL, 2021);
        Expense itemThree = new Expense("shoe", 30, ExpenseCategory.CLOTHING, Month.APRIL, 2021);

        int itemOnePrice = 10;
        int itemTwoPrice = 20;
        int itemThreePrice = 30;

        ExpenseManager amazingExpenses = new ExpenseManager();
        assertEquals(0, amazingExpenses.numberOfExpenses());

        amazingExpenses.addExpense(itemOne);
        amazingExpenses.addExpense(itemTwo);
        amazingExpenses.addExpense(itemThree);
        assertEquals(3, amazingExpenses.numberOfExpenses());

        Expense fetchedItem1 = amazingExpenses.getExpense(0);
        assertEquals(itemOnePrice, fetchedItem1.getAmount());

        Expense fetchedItem2 = amazingExpenses.getExpense(1);
        assertEquals(itemTwoPrice, fetchedItem2.getAmount());

        Expense fetchedItem3 = amazingExpenses.getExpense(2);
        assertEquals(itemThreePrice, fetchedItem3.getAmount());
    }

    @Test
    void testTotal() {
        Expense itemOne = new Expense("shoe", 10, ExpenseCategory.CLOTHING, Month.APRIL, 2021);
        Expense itemTwo = new Expense("shoe", 20, ExpenseCategory.CLOTHING, Month.APRIL, 2021);
        Expense itemThree = new Expense("shoe", 30, ExpenseCategory.CLOTHING, Month.APRIL, 2021);

        ExpenseManager amazingExpenses = new ExpenseManager();

        amazingExpenses.addExpense(itemOne);
        amazingExpenses.addExpense(itemTwo);
        amazingExpenses.addExpense(itemThree);

        assertEquals(60, amazingExpenses.total());
    }

//    @Test
//    public void testGetMonthlyTotalWithMultipleExpensesInListForOctober() {
//        double octoberTotal = myExpenses.getMonthlyTotal("2021-10");
//        double matchThis = shoes.getAmount() + newShoes.getAmount() + groceries.getAmount() + dinner.getAmount();
//
//        assertEquals(matchThis, octoberTotal);
//    }
//
//    @Test
//    public void testGetMonthlyTotalForJuly () {
//        double julyTotal = myExpenses.getMonthlyTotal("2021-07");
//        double matchThis =  julyRent.getAmount() + brokenArm.getAmount();
//
//        assertEquals(matchThis, julyTotal);
//    }
//
//    @Test
//    public void testGetMonthlyTotalForFoodCategory() {
//        double octoberAndFood = myExpenses.getMonthlyTotalForCategory("food", "2021-10");
//        double matchThis = groceries.getAmount() + dinner.getAmount();
//
//        assertEquals(matchThis, octoberAndFood);
//    }
//
//    @Test
//    void getMonthlyTotalForRentCategory() {
//        double julyAndRent = myExpenses.getMonthlyTotalForCategory("rent", "2021-07");
//        double matchThis = julyRent.getAmount();
//
//        assertEquals(matchThis, julyAndRent );
//    }
//
//    @Test
//    void getMonthlyTotalForMedicalCategory() {
//        double juneAndMedical = myExpenses.getMonthlyTotalForCategory("medical", "2021-06");
//        double matchThis = brokenRibs.getAmount();
//
//        assertEquals(matchThis, juneAndMedical);
//    }
//
//    @Test
//    void getMonthlyTotalForClothingCategory() {
//        double octoberAndClothing = myExpenses.getMonthlyTotalForCategory("clothing", "2021-10");
//        double matchThis = shoes.getAmount() + newShoes.getAmount();
//
//        assertEquals(matchThis, octoberAndClothing);
//    }
//
//    @Test
//    void getMonthlyTotalForEntertainmentCategory() {
//        double janAndEntertainment =  myExpenses.getMonthlyTotalForCategory("entertainment", "2021-01" );
//        double matchThis = concertTicket.getAmount();
//
//        assertEquals(matchThis, janAndEntertainment);
//    }

}
