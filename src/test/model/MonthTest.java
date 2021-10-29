package model;

import model.enums.Month;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonthTest {

    @Test
    void testAprilConstructor() {
        Month april =  Month.APRIL;
        String associatedString = april.getMonth();

        assertEquals(april, Month.valueOf(associatedString));
    }

    @Test
    void testMarchConstructor() {
        Month march =  Month.MARCH;
        String associatedString = march.getMonth();

        assertEquals(march, Month.valueOf(associatedString));
    }
}
