package lotto.domain;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class LottoNumber {

    public static final String NUMBER_OUT_OF_RANGE_EXCEPTION = "1 ~ 45 범위를 벗어나는 숫자가 입력되었습니다.";

    private static final Map<Integer, LottoNumber> CACHE = new ConcurrentHashMap<>();

    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber of(int number) {
        validateRange(number);
        return CACHE.computeIfAbsent(number, LottoNumber::new);
    }

    private static void validateRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(NUMBER_OUT_OF_RANGE_EXCEPTION);
        }
    }

    public int getNumber() {
        return number;
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
}
