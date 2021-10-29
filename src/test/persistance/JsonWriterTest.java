package persistance;

import model.Expense;
import model.ExpenseManager;
import model.enums.ExpenseCategory;
import model.enums.Month;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Code for JasonWriterTest is based on JsonSerializationDemo repo from CPSC210
public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testWriterEmptyExpenseManager() {
        try {
            ExpenseManager manager = new ExpenseManager();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyExpenseManager.json");
            writer.open();
            writer.write(manager);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyExpenseManager.json");
            manager = reader.read();
            assertEquals("main user", manager.getUser());
            assertEquals(0, manager.getNumberOfExpenses());
        } catch(IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testGeneralExpenseManager() {
        try {
            ExpenseManager manager = new ExpenseManager();
            manager.addExpense(new Expense
                    ("shoe", 10, ExpenseCategory.CLOTHING, Month.APRIL, 2021));
            manager.addExpense(new Expense
                    ("shoe", 10, ExpenseCategory.CLOTHING, Month.APRIL, 2021));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralExpenseManager.json");
            writer.open();
            writer.write(manager);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralExpenseManager.json");
            manager = reader.read();
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
            fail("Exception should not have been thrown");
        }
    }
}
