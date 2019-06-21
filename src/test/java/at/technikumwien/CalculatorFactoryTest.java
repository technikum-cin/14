package at.technikumwien;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class CalculatorFactoryTest {

    @Test
    public void testCreateInstance() {
        var calcFactory = new CalculatorFactory();
        assertEquals(calcFactory.createInstance(true).getClass(), CalculatorSimpleImpl.class);
    }
}