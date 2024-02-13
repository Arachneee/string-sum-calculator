package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SplitService {


    public static final String PREFIX_SEPARATOR = "//";
    private static final String SUFFIX_SEPARATOR = "\n";
    public static final Pattern CUSTOM_PATTERN = Pattern.compile("^" + PREFIX_SEPARATOR + "(.*)" + SUFFIX_SEPARATOR + "(.*)");
    private static final String DEFAULT_SEPARATOR = ",|;";

    public static int sum(String text) {
        if (text == null) {
            return 0;
        }

        final String separator = findSeparator(text);
        final String source = findSource(text);
        final List<String> stringNumbers = split(source, separator);

        return stringNumbers.stream()
                .mapToInt(SplitService::convertInt)
                .sum();
    }

    private static String findSeparator(String text) {
        if (isCustom(text)) {
            int preIndex = text.indexOf(PREFIX_SEPARATOR);
            int sufIndex = text.indexOf(SUFFIX_SEPARATOR);

            return text.substring(preIndex + 2, sufIndex);
        }

        return DEFAULT_SEPARATOR;
    }

    private static String findSource(String text) {
        if (isCustom((text))) {
            int index = text.indexOf(SUFFIX_SEPARATOR);
            return text.substring(index + 1);
        }

        return text;
    }

    public static boolean isCustom(String value) {
        final Matcher matcher = CUSTOM_PATTERN.matcher(value);
        return matcher.find();
    }

    public static List<String> split(String text, String separator) {
        return Arrays.stream(text.split(separator))
                .collect(Collectors.toList());
    }

    private static int convertInt(String value) {
        try {
            if (value == null || value.isBlank()) {
                return 0;
            }

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
