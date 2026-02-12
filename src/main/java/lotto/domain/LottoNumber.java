package lotto.domain;

import lotto.exception.ExceptionCode;
import lotto.exception.LottoException;

public class LottoNumber implements Comparable<LottoNumber> {
    private final static int LOTTO_MIN_NUM = 1;
    private final static int LOTTO_MAX_NUM = 45;

    private final int number;

    public LottoNumber(int number) {
        if (number < LOTTO_MIN_NUM || number > LOTTO_MAX_NUM) {
            throw new LottoException(ExceptionCode.INVALID_NUMBER_RANGE);
        }
        this.number = number;
    }

    public static int getLottoMinNum() {
        return LOTTO_MIN_NUM;
    }

    public static int getLottoMaxNum() {
        return LOTTO_MAX_NUM;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return this.number - o.number;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LottoNumber that = (LottoNumber) obj;
        return number == that.number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(number);
    }
}
