package org.vipul;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class StringCalculator {

    private int callCount = 0;

    private static final String DEFAULT_DELIMITER = "[,\\n]";
    private static final String[] ESC_CHARS = { "\t", "\r", "\f", "\b", "\\", "\"" };
    private static final int MAX_ALLOWED_NUMBER = 1000;

    public int add(String input) {
        callCount++;

        if (input == null || input.isEmpty()) {
            return 0;
        }

        String numbersPart = input;
        String delimiter = DEFAULT_DELIMITER;

        if (input.startsWith("//")) {
            int delimiterEndIndex = input.indexOf("\n");
            String delimiterSection = input.substring(2, delimiterEndIndex);
            delimiter = extractDelimiter(delimiterSection);
            numbersPart = input.substring(delimiterEndIndex + 1);
        }

        String[] numbers = numbersPart.split(delimiter);
        return calculateSum(numbers);
    }

    private String extractDelimiter(String section) {
        if (section.startsWith("[") && section.endsWith("]")) {
            if (section.contains("][")) {
                // multiple delimiters
                List<String> delimiters = new ArrayList<>();
                String[] parts = section.split("\\]\\[");
                for (String part : parts) {
                    String raw = part.replaceAll("[\\[\\]]", "");
                    delimiters.add(Pattern.quote(raw));
                }
                return String.join("|", delimiters);
            } else {
                String raw = section.substring(1, section.length() - 1);
                return Pattern.quote(raw);
            }
        } else {
            if (Arrays.asList(ESC_CHARS).contains(section)) {
                return "\\" + section;
            }
            return section;
        }
    }

    private int calculateSum(String[] numbers) {
        int sum = 0;
        StringBuilder negatives = new StringBuilder();

        for (String number : numbers) {
            int value = parseInt(number.trim());

            if (value < 0) {
                if (!negatives.isEmpty()) negatives.append(",");
                negatives.append(value);
            } else if (value <= MAX_ALLOWED_NUMBER) {
                sum += value;
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
