package org.example;

import java.util.Arrays;
import java.util.regex.Pattern;

public class SplitService {

    public static final Pattern NUMBERIC_PATTERN = Pattern.compile("^[0-9]+$");

    public static final String PREFIX_SEPARATOR = "//";
    private static final String SUFFIX_SEPARATOR = "\n";

    public static int sum(String text) {
        if (text == null || text.isBlank()) {
            return 0;
        }

        String[] arr = split(text);

        if (arr.length == 0) {
            return 0;
        }

        return Arrays.stream(arr)
                .mapToInt(SplitService::convertInt)
                .sum();
    }

    public static String[] split(String text) {
        boolean condition = isCustom(text);
        if (condition) {
            int preIndex = text.indexOf(PREFIX_SEPARATOR);
            int sufIndex = text.indexOf(SUFFIX_SEPARATOR);

            String substring = findSubstring(text);
            if (substring.isBlank()) {
                return new String[]{"0"};
            }

            return substring.split(text.substring(preIndex + 2, sufIndex));
        } else {
            return text.split(",|:");
        }
    }

    public static boolean isCustom(String value) {
        if (value.contains("//") && value.contains("\n")) {
            int preIndex = value.indexOf(PREFIX_SEPARATOR);
            int sufIndex = value.indexOf(SUFFIX_SEPARATOR);
            if (preIndex < sufIndex) {
                return true;
            }
            throw new IllegalArgumentException("입력이 잘못되었습니다.");
        }
        return false;
    }

    public static String findSubstring(String text) {
        int index = text.indexOf(SUFFIX_SEPARATOR);
        return text.substring(index + 1);
    }

    private static int convertInt(String value) {
        try {
            int number = Integer.parseInt(value);
            if (number < 0) {
                throw new RuntimeException();
            }

            return number;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
