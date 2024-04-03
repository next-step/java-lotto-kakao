package lotto.domain;

import java.util.Objects;

public class LottoNumber {
    private final int number;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    public LottoNumber(int number) {
        validateBall(number);
        this.number = number;
    }

    private void validateBall(int number) {
        if (number < MIN_LOTTO_NUMBER) {
            throw new RuntimeException("공은 " + MIN_LOTTO_NUMBER + "이상의 정수여야 합니다.");
        }

        if (number > MAX_LOTTO_NUMBER) {
            throw new RuntimeException("공은 " + MAX_LOTTO_NUMBER + "이하의 정수여야 합니다.");
        }
    }

    public Integer toInteger() {
        return number;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        LottoNumber lottoNumber = (LottoNumber)other;
        return this.number == lottoNumber.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
