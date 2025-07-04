package org.vipul;


import java.util.Arrays;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class StringCalculator {

    private int callCount = 0;
    public String DELIMITER = "[,\\n]"; // Default delimiters : "," and "\n"
    public final String[] ESC_CHARS = new String[]{"\t" , "\r" , "\f" , "\b" , "\\" , "\"" };
    public final int MAX_ALLOWED_NUMBER = 1000;

    private int calculateSum(String[] numbers) {
        int temp_sum = 0;
        StringBuilder negatives = new StringBuilder();

        for (String number : numbers) {
            int intnum = parseInt(number);
            if (intnum < 0) {
                if (!negatives.isEmpty()) {
                    negatives.append(",");
                }
                negatives.append(intnum);
            }
            if(intnum <= MAX_ALLOWED_NUMBER){
                temp_sum += intnum;
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negatives);
        }

        return temp_sum;
    }

    public int add(String input) {
        callCount++;
        if(input.isEmpty()){
            return 0;
        }

        String[] numbers;

        if(input.startsWith("//")) {
            int del_start_index = input.indexOf("//");
            int del_end_index = input.indexOf("\n");

            String delimiterSection = input.substring(del_start_index + 2, del_end_index);

            // Handle delimiters of any length between []
            if (delimiterSection.startsWith("[") && delimiterSection.endsWith("]")) {
                DELIMITER = delimiterSection.substring(1, delimiterSection.length() - 1);
                // Escape special regex characters
                DELIMITER = Pattern.quote(DELIMITER);
            } else {
                DELIMITER = delimiterSection;
                if(Arrays.asList(ESC_CHARS).contains(DELIMITER)){
                    DELIMITER = "\\" + DELIMITER;
                }
            }

            input = input.substring(del_end_index + 1);
        }
        numbers = input.split(DELIMITER);

        return calculateSum(numbers);
    }

    public int getCalledCount() {
        return callCount;
    }

}