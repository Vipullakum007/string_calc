package org.vipul;


import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class StringCalculator {
    public int add(String input) {
        if(input.isEmpty()){
            return 0;
        }

        int sum = 0;
        String delimiter = "[,\\n]";
        String[] numbers;
        if(input.startsWith("//")) {
            int del_start_index = input.indexOf("//");
            int del_end_index = input.indexOf("\n");

            delimiter = input.substring(del_start_index + 2, del_end_index);

            input = input.substring(del_end_index + 1);

            String[] escapeCharacters = new String[]{ "\t", "\r", "\f", "\b", "\\", "\""};
            if (Arrays.asList(escapeCharacters).contains(delimiter)) {
                delimiter = "\\" + delimiter;
            }
        }
        numbers = input.split(delimiter);
        for(String number : numbers) {
            int intnum = parseInt(number);
            if(intnum < 0){
                throw new IllegalArgumentException("Negative numbers not allowed: " + intnum);
            }
            sum += parseInt(number);
        }
        return sum;
    }
}