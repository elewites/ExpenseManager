package model;

import model.enums.ExpenseCategory;
import model.enums.Month;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpenseTest {

    @Test
    public void testConstructor() {
        String description = "shoe";
        double amount = 10;
        ExpenseCategory category = ExpenseCategory.CLOTHING;
        Month month = Month.APRIL;
        int year = 2021;

        Expense shoe = new Expense(description, amount, category, month, year);
        String fetchedDescription = shoe.getDescription();
        ExpenseCategory fetchedCategory = shoe.getCategory();
        Month fetchedMonth = shoe.getMonth();

        assertEquals(amount, shoe.getAmount());
        assertEquals(year, shoe.getYear());
        assertTrue(description.equals(fetchedDescription));
        assertTrue(category.equals(fetchedCategory));
        assertTrue(month.equals(fetchedMonth));
    }

    @Test
    void testToString() {
        String description = "shoe";
        double amount = 10;
        ExpenseCategory category = ExpenseCategory.CLOTHING;
        Month month = Month.APRIL;
        int year = 2021;

        Expense shoe = new Expense(description, amount, category, month, year);

        String formatted = shoe.toString();
        String expected = "Expense { description: shoe, amount: $10.0, category: CLOTHING, month: APRIL, year: 2021 }";
        assertEquals(expected, formatted);
    }

}
