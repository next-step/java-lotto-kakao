package lotto.domain;

import lotto.exception.LottoErrorCode;
import lotto.exception.LottoException;

import java.util.HashMap;
import java.util.Map;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int LOTTO_MIN_NUM = 1;
    private static final int LOTTO_MAX_NUM = 45;

    private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();

    static {
        for (int i = LOTTO_MIN_NUM; i <= LOTTO_MAX_NUM; i++) {
            CACHE.put(i, new LottoNumber(i));
        }
    }

    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber valueOf(int number) {
        if (number < LOTTO_MIN_NUM || number > LOTTO_MAX_NUM) {
            throw new LottoException(LottoErrorCode.INVALID_NUMBER_RANGE);
        }

        return CACHE.get(number);
    }

    public static int getMinNum() {
        return LOTTO_MIN_NUM;
    }

    public static int getMaxNum() {
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
