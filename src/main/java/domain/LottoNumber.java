package domain;

import enumeration.LottoCondition;

import java.util.Objects;

public final class LottoNumber implements Comparable<LottoNumber> {
    private final int value;

    public static LottoNumber of(int value) {
        return new LottoNumber(value);
    }

    private LottoNumber(int value) {
        validateValue(value);
        this.value = value;
    }

    private void validateValue(int value) {
        int begin = LottoCondition.BEGIN.value();
        int end = LottoCondition.END.value();
        if (value < begin || value > end) {
            throw new IllegalArgumentException("로또 번호는 " + begin + "이상 " + end + "이하여야 합니다.");
        }
    }

    public int value() {
        return value;
    }

    public boolean isMatched(LottoNumber lottoNumber) {
        return equals(lottoNumber);
    }

    @Override
    public int compareTo(LottoNumber number) {
        return Integer.compare(value, number.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber number = (LottoNumber) o;
        return value == number.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
