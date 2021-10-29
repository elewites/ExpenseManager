package model;

import model.enums.ExpenseCategory;
import model.enums.Month;
import org.junit.jupiter.api.Test;
import persistance.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ExpenseManager manager = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyExpenseManager() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyExpenseManager.json");
        try {
            ExpenseManager manager = reader.read();
            assertEquals("main user", manager.getUser());
            assertEquals(0, manager.getNumberOfExpenses());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralExpenseManager.json");
        try {
            ExpenseManager manager = reader.read();
            assertEquals("main user", manager.getUser());

            List<Expense> expenses = manager.getExpenses();

            assertEquals(2, expenses.size());

            Expense expenseOne = expenses.get(0);
            Expense expenseTwo = expenses.get(1);

            assertTrue("shoe".equals(expenseOne.getDescription()));
            assertEquals(10, expenseOne.getAmount());
            assertTrue(ExpenseCategory.CLOTHING.equals(expenseOne.getCategory()));
            assertTrue(Month.APRIL.equals(expenseOne.getMonth()));
            assertEquals(2021, expenseOne.getYear());

            assertTrue("shoe".equals(expenseTwo.getDescription()));
            assertEquals(10, expenseTwo.getAmount());
            assertTrue(ExpenseCategory.CLOTHING.equals(expenseTwo.getCategory()));
            assertTrue(Month.APRIL.equals(expenseTwo.getMonth()));
            assertEquals(2021, expenseTwo.getYear());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
