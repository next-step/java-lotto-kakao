package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputParser {

    private static final String DEFAULT_DELIMITER_REGEX = ",|:";
    private static final Pattern CUSTOM_FORMAT = Pattern.compile("^//(.)\n(.*)$");

    public static final String EMPTY_CUSTOM_DELIMITER_MSG = "커스텀 구분자가 없습니다.";
    public static final String INVALID_CUSTOM_DELIMITER_LENGTH_MSG = "커스텀 구분자는 1글자여야 합니다.";
    public static final String NUMBER_CUSTOM_DELIMITER_MSG = "커스텀 구분자는 숫자가 될 수 없습니다.";
    public static final String DASH_CUSTOM_DELIMITER_MSG = "커스텀 구분자는 '-'가 될 수 없습니다.";

    private InputParser(String input) {
    }

    public static ParsedInput parse(String input) {
        Matcher matcher = CUSTOM_FORMAT.matcher(input);
        if (!matcher.find()) {
            return new ParsedInput(input, DEFAULT_DELIMITER_REGEX);
        }

        String customDelimiter = matcher.group(1);
        String body = matcher.group(2);

        validateCustomDelimiter(customDelimiter);
        return new ParsedInput(body, Pattern.quote(customDelimiter));
    }

    private static void validateCustomDelimiter(String delimiter) {
        if (delimiter == null) {
            throw new IllegalArgumentException(EMPTY_CUSTOM_DELIMITER_MSG);
        }

        if (delimiter.length() != 1) {
            throw new IllegalArgumentException(INVALID_CUSTOM_DELIMITER_LENGTH_MSG);
        }

        char ch = delimiter.charAt(0);
        if (Character.isDigit(ch)) {
            throw new IllegalArgumentException(NUMBER_CUSTOM_DELIMITER_MSG);
        }

        if (ch == '-') {
            throw new IllegalArgumentException(DASH_CUSTOM_DELIMITER_MSG);
        }
    }
}
