package org.vipul;


import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class StringCalculator {
    private int callCount = 0;

    public int add(String input) {
        callCount++;
        if(input.isEmpty()){
            return 0;
        }

        int sum = 0;
        String delimiter = "[,\\n]";
        String[] numbers;
        StringBuilder negatives = new StringBuilder();

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
                if(!negatives.isEmpty()){
                    negatives.append(",");
                }
                negatives.append(intnum);
            }
            if(intnum <= 1000){
                sum += parseInt(number);
            }
        }
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negatives);
        }
        return sum;
    }

    public int getCalledCount() {
        return callCount;
    }

}