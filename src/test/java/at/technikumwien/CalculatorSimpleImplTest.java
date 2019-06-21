package at.technikumwien;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorSimpleImplTest {

    private CalculatorSimpleImpl calc;

    @BeforeEach
    public void setUp() throws Exception {
        calc = new CalculatorSimpleImpl();
    }

    @Test
    public void testSumOfTwoNumbers() {
        assertEquals(18, calc.sum(10, 8));
    }

    @Test
    public void testSumOfTooManyNumbers() {
        assertThrows(IllegalArgumentException.class, () -> calc.sum(1,2,3));
    }
}