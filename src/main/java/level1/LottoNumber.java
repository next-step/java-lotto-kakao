package level1;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {

    private final int value;

    private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();

    static {
        IntStream.rangeClosed(Constant.LOTTERY_MIN_VALUE, Constant.LOTTERY_MAX_VALUE)
                .forEach(i -> CACHE.put(i, new LottoNumber(i)));
    }

    private LottoNumber(int value) {
        this.value = value;
    }

    public static LottoNumber valueOf(int value) {
        validateRange(value);
        return CACHE.get(value);
    }

    public static LottoNumber valueOf(String stringNumber) {
        return valueOf(parse(stringNumber));
    }

    private static int parse(String stringNumber) {
        try {
            return Integer.parseInt(stringNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값은 허용되지 않습니다.");
        }
    }

    private static void validateRange(int value) {
        if (value < Constant.LOTTERY_MIN_VALUE || value > Constant.LOTTERY_MAX_VALUE) {
            throw new IllegalArgumentException("로또 번호는 1 - 45 범위 숫자만 가능합니다.");
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(this.value, other.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoNumber that)) return false;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}