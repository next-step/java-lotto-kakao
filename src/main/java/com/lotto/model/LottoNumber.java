package com.lotto.model;

import java.util.*;

public class LottoNumber implements Comparable<LottoNumber> {

    public static final int LOTTO_NUMBER_MIN = 1;
    public static final int LOTTO_NUMBER_MAX = 45;
    private static final Map<Integer, LottoNumber> LOTTO_NUMBER_CACHE = new HashMap<>();
    private static final List<LottoNumber> LOTTO_NUMBER_CACHE_VALUES;

    static {
        for (int i = LOTTO_NUMBER_MIN; i <= LOTTO_NUMBER_MAX; i++) {
            LOTTO_NUMBER_CACHE.put(i, new LottoNumber(i));
        }
        LOTTO_NUMBER_CACHE_VALUES = new ArrayList<>(LOTTO_NUMBER_CACHE.values());
    }

    private final int number;

    public LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    public static LottoNumber valueOf(int number) {
        LottoNumber lottoNumber = LOTTO_NUMBER_CACHE.get(number);

        if (Objects.isNull(lottoNumber)) {
            lottoNumber = new LottoNumber(number);
        }
        return lottoNumber;
    }

    public static List<LottoNumber> values() {
        return LOTTO_NUMBER_CACHE_VALUES;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(number, o.number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    private void validateRange(int number) {
        if (number < LOTTO_NUMBER_MIN || number > LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException("로또 번호는 1부터 45까지 가능합니다.");
        }
    }
}
