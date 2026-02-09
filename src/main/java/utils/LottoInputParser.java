package utils;

import java.util.HashSet;
import java.util.Set;

public class LottoInputParser {
    public static Set<Integer> parseLottoFormat(String rawInput) {
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

    private static Set<Integer> parseTokens(String rawInput) {
        Set<Integer> numbers = new HashSet<>();
        String[] tokens = rawInput.split(",");

        for (String token : tokens) {
            numbers.add(parseInt(token));
        }
        return numbers;
    }

    private static int parseInt(String token) {
        return Integer.parseInt(token.trim());
    }
}
