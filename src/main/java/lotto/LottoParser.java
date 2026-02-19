package lotto;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LottoParser {
    private static final String LOTTO_DELIMITER_MESSAGE =
            "로또 번호 6개는 쉼표(,)로 구분해 입력해 주세요. 예: 1, 2, 3, 4, 5, 6";
    private static final String LOTTO_NON_NUMERIC_MESSAGE =
            "로또 번호는  1~45 사이의 숫자만 입력해 주세요.";
    private static final int LOTTO_NUMBER_COUNT = 6;

    public Lotto parse(String lottoNumbers) {
        String[] tokens = lottoNumbers.split(",");
        validateDelimiter(tokens);

        return new Lotto(Arrays.stream(tokens)
                .map(String::trim)
                .map(this::toLottoNumber)
                .collect(Collectors.toList()));
    }

    private void validateDelimiter(String[] tokens) {
        boolean hasBlankToken = Arrays.stream(tokens)
                .map(String::trim)
                .anyMatch(String::isEmpty);
        if (tokens.length != LOTTO_NUMBER_COUNT || hasBlankToken) {
            throw new IllegalArgumentException(LOTTO_DELIMITER_MESSAGE);
        }
    }

    private LottoNumber toLottoNumber(String number) {
        try {
            return new LottoNumber(Integer.parseInt(number));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(LOTTO_NON_NUMERIC_MESSAGE);
        }
    }
}
