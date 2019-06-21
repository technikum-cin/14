package at.technikumwien;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorExtendedImplTest {

    private CalculatorExtendedImpl calc;

    @BeforeEach
    public void setUp() throws Exception {
        calc = new CalculatorExtendedImpl();
    }

    @Test
    public void testSumOfSixNumbers() {
        assertEquals(21, calc.sum(1,2,3,4,5,6));
    }

    @Test
    public void testSumOfNegativeNumbers() {
        assertEquals(1, calc.sum(-1,2,-3,4,5,-6));
    }

    @Test
    public void testOfNull() {
        assertEquals(0, calc.sum());
    }
}