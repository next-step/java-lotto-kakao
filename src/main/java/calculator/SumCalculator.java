package calculator;

public class SumCalculator {

    public static final String INVALID_NUMBER_FORMAT_MSG = "숫자 이외의 값이 포함되어 있습니다: ";
    public static final String TOKEN_NEGATIVE_MSG = "음수는 입력할 수 없습니다.";

    private SumCalculator() {
    }

    public static int sum(String body, String delimiterRegex) {
        String[] tokens = tokenize(body, delimiterRegex);
        return addAll(tokens);
    }

    private static String[] tokenize(String body, String delimiterRegex) {
        if (body.isEmpty()) {
            return new String[0];
        }
        return body.split(delimiterRegex);
    }

    private static int addAll(String[] tokens) {
        int result = 0;
        for (String token: tokens) {
            int number = parseNumber(token);
            validateNonNegative(number);
            result += number;
        }
        return result;
    }

    private static int parseNumber(String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new RuntimeException(INVALID_NUMBER_FORMAT_MSG + token, e);
        }
    }

    private static void validateNonNegative(int number) {
        if (number < 0) {
            throw new RuntimeException(TOKEN_NEGATIVE_MSG);
        }
    }
}
