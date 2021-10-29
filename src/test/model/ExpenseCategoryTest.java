package model;

import model.enums.ExpenseCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseCategoryTest {

    @Test
    void testFoodConstructor() {
        ExpenseCategory food =  ExpenseCategory.FOOD;
        String associatedString = food.getCategory();

        assertEquals(food, ExpenseCategory.valueOf(associatedString));
    }

    @Test
    void testRentConstructor() {
        ExpenseCategory rent =  ExpenseCategory.RENT;
        String associatedString = rent.getCategory();

        assertEquals(rent, ExpenseCategory.valueOf(associatedString));
    }
}
