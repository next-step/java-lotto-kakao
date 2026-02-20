package lotto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final List<LottoNumber> CACHED_NUMBERS = IntStream.range(MIN_NUMBER, MAX_NUMBER+1)
            .mapToObj(LottoNumber::new)
            .collect(Collectors.toList());
    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber from(int number) {
        validateRangeOfNumber(number);
        return CACHED_NUMBERS.get(number - MIN_NUMBER);
    }

    public static List<LottoNumber> all() {
        return CACHED_NUMBERS;
    }

    public int toNumber() {
        return this.number;
    }

    private static void validateRangeOfNumber(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("번호는 1~45 사이의 숫자를 입력해 주세요.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
