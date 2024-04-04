package lotto.domain;

import java.util.*;

public class LottoNumber {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();

    static {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER ; i++) {
            CACHE.put(i, new LottoNumber(i));
        }
    }

    private final int number;

    private LottoNumber(int number) {
        validateBall(number);
        this.number = number;
    }

    public static LottoNumber valueOf(final int number) {
        LottoNumber lottoNumber = CACHE.get(number);

        if (Objects.isNull(lottoNumber)) {
            lottoNumber = new LottoNumber(number);
        }

        return lottoNumber;
    }

    public static List<LottoNumber> getValues() {
        return new ArrayList<>(CACHE.values());
    }

    public Integer getValue() {
        return number;
    }

    private void validateBall(int number) {
        if (number < MIN_LOTTO_NUMBER) {
            throw new IllegalArgumentException("공은 " + MIN_LOTTO_NUMBER + "이상의 정수여야 합니다.");
        }

        if (number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("공은 " + MAX_LOTTO_NUMBER + "이하의 정수여야 합니다.");
        }
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
}
