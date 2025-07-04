package org.vipul;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void support_any_given_delimiter(){
        assertEquals(3,stringCalculator.add("//;\n1;2"));
        assertEquals(6,stringCalculator.add("//;\n1;2;3"));
    }

    @Test
    public void given_diff_delimiter_should_work_if_its_escape_char(){
        assertEquals(3,stringCalculator.add("//\\\n1\\2"));
        assertEquals(6,stringCalculator.add("//\t\n1\t2\t3"));
    }

    @Test
    public void negative_number_should_throw_exception(){
        assertThrows(IllegalArgumentException.class,()->stringCalculator.add("3,4,-8"),"Negatives not allowed -8 ");
        assertThrows(IllegalArgumentException.class,()->stringCalculator.add("-8"),"Negatives not allowed -8 ");
    }

    @Test
    public void more_than_one_negative_numbers_should_return_all_those_numbers_with_exception(){
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class,
                () -> stringCalculator.add("-8,2,3,-1"));
        assertEquals("Negatives not allowed: -8,-1", ex1.getMessage());

        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class,
                () -> stringCalculator.add("1,-2,3,-4,-5,-6,7,8,-9"));
        assertEquals("Negatives not allowed: -2,-4,-5,-6,-9", ex2.getMessage());
    }

    @Test
    public void add_method_should_return_number_of_times_it_is_called() {

        stringCalculator.add("1,2");
        stringCalculator.add("3,4");
        stringCalculator.add("5");

        assertEquals(3, stringCalculator.getCalledCount());
    }

    @Test
    public void numbers_greater_than_1000_should_ignored(){
        assertEquals(2,stringCalculator.add("1001,2"));
        assertEquals(6,stringCalculator.add("1001,1,2,3"));
    }
}
