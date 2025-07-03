package org.vipul;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {
    StringCalculator stringCalculator = new StringCalculator();


    @Test
    public void empty_string_should_return_0() {
        assertEquals(0,stringCalculator.add(""));
    }
}
