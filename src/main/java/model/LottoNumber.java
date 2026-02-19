package model;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final String INVALID_LOTTO_NUMBER_MESSAGE = "로또 번호는 1~45까지의 숫자여야 한다.";
    private static final Map<Integer, LottoNumber> CACHE = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
            .boxed()
            .collect(Collectors.toMap(Function.identity(), LottoNumber::new));

    private final Integer value;

    private LottoNumber(Integer value) {
        this.value = value;
    }

    public static LottoNumber of(Integer value) {
        validate(value);
        return CACHE.get(value);
    }

    private static void validate(Integer value) {
        if (value == null || value < MIN_LOTTO_NUMBER || value > MAX_LOTTO_NUMBER) {
            throw new RuntimeException(INVALID_LOTTO_NUMBER_MESSAGE);
        }
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoNumber)) return false;
        LottoNumber that = (LottoNumber) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
