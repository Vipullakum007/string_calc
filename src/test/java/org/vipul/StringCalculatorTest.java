package org.vipul;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {
    StringCalculator stringCalculator = new StringCalculator();


    @Test
    public void empty_string_should_return_0() {
        assertEquals(0,stringCalculator.add(""));
    }

    @Test
    public void single_number_string_should_return_that_number(){
        assertEquals(11,stringCalculator.add("11"));
        assertEquals(50,stringCalculator.add("50"));
    }

    @Test
    public void two_numbers_string_should_return_sum_of_two_numbers(){
        assertEquals(7,stringCalculator.add("3,4"));
        assertEquals(50,stringCalculator.add("10,40"));
    }

    @Test
    public void any_amount_numbers_string_should_return_sum_of_all_those_numbers(){
        assertEquals(6,stringCalculator.add("2,4"));
        assertEquals(12,stringCalculator.add("2,4,6"));
        assertEquals(10,stringCalculator.add("1,2,3,4"));
        assertEquals(55,stringCalculator.add("1,2,3,4,5,6,7,8,9,10"));
    }

    @Test
    public void newline_as_delimiter_also_work(){
        assertEquals(5,stringCalculator.add("2\n3"));
        assertEquals(12,stringCalculator.add("2\n4\n6"));
        assertEquals(60, stringCalculator.add("10\n20,30"));
    }
}
