package lotto.domain;

import java.util.Objects;

public class LottoCount {

    public static final String NEGATIVE_COUNT_MSG = "로또 구매 수는 0 이상이어야 합니다.";
    public static final String NEGATIVE_MANUAL_COUNT_MSG = "수동 구매 수는 0 이상이어야 합니다.";
    public static final String TOO_MANY_MANUAL_COUNT_MSG = "수동 구매 수가 전체 구매 수보다 클 수 없습니다.";


    private final int value;

    public LottoCount(int value) {
        this.value = value;
    }

    public static LottoCount of(int value) {
        if (value < 0) {
            throw new IllegalArgumentException(NEGATIVE_COUNT_MSG);
        }
        return new LottoCount(value);
    }

    public static LottoCount manual(int value) {
        if (value < 0) {
            throw new IllegalArgumentException(NEGATIVE_MANUAL_COUNT_MSG);
        }
        return new LottoCount(value);
    }

    public int value() {
        return value;
    }

    public void validateNotExceeded(LottoCount total) {
        if (this.value > total.value) {
            throw new IllegalArgumentException(TOO_MANY_MANUAL_COUNT_MSG);
        }
    }

    public LottoCount minus(LottoCount other) {
        int result = this.value - other.value;
        if (result < 0) {
            throw new IllegalArgumentException(TOO_MANY_MANUAL_COUNT_MSG);
        }
        return LottoCount.of(result);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LottoCount that = (LottoCount) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
