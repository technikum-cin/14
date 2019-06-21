package at.technikumwien;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

@Tag("integration")
public class CalculatorFactoryTest {

    @Test
    public void testCreateInstance() {
        var calcFactory = new CalculatorFactory();
        assertEquals(calcFactory.createInstance(true).getClass(), CalculatorSimpleImpl.class);
    }
}