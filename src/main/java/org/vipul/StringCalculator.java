package org.vipul;


import static java.lang.Integer.parseInt;

public class StringCalculator {
    public int add(String input) {
        if(input.isEmpty()){
            return 0;
        }
        int sum = 0;
        if(input.startsWith("//")){
            int del_start_index = input.indexOf("//");
            int del_end_index = input.indexOf("\n");

            String delimiter = input.substring(del_start_index+2, del_end_index);

            String org_string = input.substring(del_end_index+1, input.length());

            String[] numbers = org_string.split(delimiter);
            for(String number : numbers) {
                sum += parseInt(number);
            }
        }
        else {
            String[] numbers = input.split("[,\\n]");
            for (String number : numbers) {
                sum += parseInt(number);
            }
        }
        return sum;
    }
}