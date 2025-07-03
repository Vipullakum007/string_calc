package org.vipul;


import static java.lang.Integer.parseInt;

public class StringCalculator {
    public int add(String input) {
        if(input.isEmpty()){
            return 0;
        }
        String[] numbers = input.split(",");
        if (numbers.length == 1) {
            return parseInt(numbers[0]);
        }
        int sum = parseInt(numbers[0]) + parseInt(numbers[1]);
        return sum;
    }
}