package lotto;

import java.util.Objects;

public final class LottoNumber implements Comparable<LottoNumber> {
    private static final int MIN = 1;
    private static final int MAX = 45;

    private static final LottoNumber[] CACHE = new LottoNumber[MAX + 1];

    static {
        for (int i = MIN; i <= MAX; i++) {
            CACHE[i] = new LottoNumber(i);
        }
    }

    private final int value;

    private LottoNumber(int value) {
        this.value = value;
    }

    public static LottoNumber of(int value) {
        validate(value);
        return CACHE[value];
    }

    private static void validate(int value) {
        if (value < MIN || value > MAX) {
            throw new IllegalArgumentException(
                    String.format("로또 번호는 %d부터 %d 사이여야 합니다. 입력값: %d", MIN, MAX, value)
            );
        }
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(this.value, other.value);
    }

    @Override
    public String toString() {
        return "" + value;
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LottoNumber that)) return false;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
