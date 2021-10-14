package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseTest {

    @Test
    public void testConstructor() {
        double a = 10;
        String c = "clothing";
        String m = "2021-10";
        Expense shoe = new Expense(a, c, m);
        assertEquals(a, shoe.getAmount());

        String fetchedCategory = shoe.getCategory();
        c.equals(fetchedCategory);
    }
}
