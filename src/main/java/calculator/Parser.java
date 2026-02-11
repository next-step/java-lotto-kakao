package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final String DEFAULT_DELIMITER = ",|:";
    private static final Pattern CUSTOM_PATTERN = Pattern.compile("//(.)\n(.*)");

    public String[] split(String input) {
        Matcher m = CUSTOM_PATTERN.matcher(input);

        if (m.find()) {
            String customDelimiter = m.group(1);
            String finalDelimiter = DEFAULT_DELIMITER + "|" + Pattern.quote(customDelimiter);
            String targetInput = m.group(2);
            return targetInput.split(finalDelimiter);
        }

        return input.split(DEFAULT_DELIMITER);
    }
}