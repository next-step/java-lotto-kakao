package utils;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    public static Long parseMoney(String rawInput) {
        try {
            return parseLong(rawInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입금액은 반드시 숫자여야 합니다.");
        }
    }

    public static List<Integer> parseLottoFormat(String rawInput) {
        try {
            return parseTokens(rawInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("로또 입력은 반드시 숫자와 ','로 이루어져야 합니다.");
        }
    }

    public static Integer parseBonusNumberFormat(String rawInput) {
        try {
            return parseInt(rawInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("보너스 번호는 반드시 숫자여야 합니다.");
        }
    }

    private static List<Integer> parseTokens(String rawInput) {
        return Arrays.stream(rawInput.split(","))
                .map(InputParser::parseInt)
                .toList();
    }

    private static int parseInt(String token) {
        return Integer.parseInt(token.trim());
    }

    private static long parseLong(String token) {
        return Long.parseLong(token.trim());
    }
}
