package calculator;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {

    private Parser() {
    }

    public static Number parse(String input) {
        Separator separator = new Separator(input);
        if (separator.hasCustomSeparator()) {
            input = input.substring(4);
        }
        validate(input, separator);
        String parsedInput = parseSeparator(input, separator);
        validateConsecutiveSeparators(parsedInput);
        return new Number(parsedInput);
    }

    private static void validate(String input, Separator separator) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c) && !separator.contains(String.valueOf(c))) {
                throw new IllegalArgumentException("등록되지 않은 커스텀 구분자가 입력되었습니다.");
            }
        }
    }

    private static String parseSeparator(String input, Separator separator) {
        String regex = separator.getSeparators().stream()
                .map(Pattern::quote)
                .collect(Collectors.joining("", "[", "]"));

        return input.replaceAll(regex, " ");
    }

    private static void validateConsecutiveSeparators(String input) {
        if (input.contains("  ")) {
            throw new IllegalArgumentException("구분자는 연속적으로 사용할 수 없습니다.");
        }
    }
}
